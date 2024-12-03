import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.matching.Regex

object Day03 {
  private val pattern = new Regex("""mul\(([^()]+)\)""")

  def main(args: Array[String]): Unit = {
    val filePath = "input.txt"
    val input = readFileAsString(filePath)

    val part1Result = solvePart1(input)
    val part2Result = solvePart2(input)

    println(s"Part 1: $part1Result")
    println(s"Part 2: $part2Result")
  }

  def solvePart1(input: String): Int = {
    val calculations = pattern.findAllMatchIn(input).map(_.group(1)).toList

    val validCalculations = ListBuffer[(Int, Int)]()

    calculations.foreach { value =>
      val parts = value.split(',')

      if (parts.length == 2) {
        val firstNumber = parts(0).toIntOption
        val secondNumber = parts(1).toIntOption

        if (firstNumber.isDefined && secondNumber.isDefined) {
          validCalculations += ((firstNumber.get, secondNumber.get))
        }
      }
    }

    validCalculations.map { case (a, b) => a * b }.sum
  }

  def solvePart2(input: String): Int = {
    val calculations = pattern.findAllMatchIn(input).map(_.group(1)).toList
    val validCalculations = ListBuffer[String]()

    calculations.foreach { value =>
      val parts = value.split(',')

      if (parts.length == 2) {
        val firstNumber = parts(0).toIntOption
        val secondNumber = parts(1).toIntOption

        if (firstNumber.isDefined && secondNumber.isDefined) {
          validCalculations += value
        }
      }
    }

    val commandParts = splitWithDelimiters(input, validCalculations)

    var result = 0
    var previousCommand = "do()"

    commandParts.zipWithIndex.foreach { case (part, idx) =>
      if (idx != 0 && idx % 2 != 0) {
        val newCommand = getLastCommand(commandParts(idx - 1))
        val command = newCommand.getOrElse(previousCommand)
        previousCommand = command

        if (command == "do()") {
          val calculationParts = part.split(',')
          if (calculationParts.length == 2) {
            val firstNumber = calculationParts(0).toIntOption.getOrElse(0)
            val secondNumber = calculationParts(1).toIntOption.getOrElse(0)
            result += firstNumber * secondNumber
          }
        }
      }
    }

    result
  }

  private def splitWithDelimiters(input: String, delimiters: ListBuffer[String]): List[String] = {
    var result = List(input)

    for (delimiter <- delimiters) {
      result = result.flatMap { str =>
        val parts = str.split(delimiter).toList
        if (parts.size == 1) {
          parts
        } else {
          parts.init ++ List(delimiter) ++ parts.lastOption.toList
        }
      }
    }

    result
  }

  private def getLastCommand(commandString: String): Option[String] = {
    val regex = "(do\\(\\)|don't\\(\\))".r
    val matchedCommands = regex.findAllIn(commandString).toList
    matchedCommands.lastOption
  }

  private def readFileAsString(filePath: String): String = {
    Source.fromFile(filePath).mkString
  }
}
