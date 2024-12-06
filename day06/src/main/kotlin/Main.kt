import java.io.File


fun main(args: Array<String>) {
    val file = File("input.txt")
    val input = file.readText()

    println("Part 1: " + solvePart1(input))
}