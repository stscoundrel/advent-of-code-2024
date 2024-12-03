import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.matching.Regex

object Day03 {
  private val pattern = new Regex("""mul\(([^()]+)\)""")

  def main(args: Array[String]): Unit = {
    val filePath = "input.txt"
    val input = readFileAsString(filePath)

    val part1Result = solvePart1(input)

    println(s"Part 1: $part1Result")
  }

  def solvePart1(input: String): Int = {
    val calculations = pattern.findAllMatchIn(input).map(_.group(1)).toList

    val validCalculations = ListBuffer[(Int, Int)]()

    calculations.foreach { value =>
      val parts = value.split(',')

      if (parts.length != 2) {
        false
      } else {
        val firstNumber = parts(0).toIntOption
        val secondNumber = parts(1).toIntOption

        if (firstNumber.isDefined && secondNumber.isDefined) {
          validCalculations += ((firstNumber.get, secondNumber.get))
        }
      }
    }

    validCalculations.map { case (a, b) => a * b }.sum
  }

  private def readFileAsString(filePath: String): String = {
    Source.fromFile(filePath).mkString
  }
}
