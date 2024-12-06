const val guard = '^'
const val blocked = '#'

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

enum class PositionType {
    FREE,
    BLOCKED,
    OUT;

    companion object {
        fun from(value: Char): PositionType {
            return if (value == blocked) {
                BLOCKED
            } else {
                FREE
            }
        }
    }
}

fun isValidIndex(row: Int, col: Int, matrix: Array<Array<Char>>): Boolean {
    return row in matrix.indices && col in matrix[row].indices
}

fun inputToMatrix(input: String): Array<Array<Char>> {
    return input.lines()
        .filter { it.isNotBlank() }
        .map { line: String -> line.toCharArray().toTypedArray() }
        .toTypedArray()
}

fun findGuardPosition(matrix: Array<Array<Char>>): Pair<Int, Int> {
    matrix.forEachIndexed { index, row ->
        if (row.indexOf(guard) != -1) {
            return Pair(index, row.indexOf(guard))
        }
    }

    throw Error("Taivas varjele koodaat huonosti")
}

fun rotateDirection(direction: Direction): Direction {
    return when (direction) {
        Direction.UP -> Direction.RIGHT
        Direction.RIGHT -> Direction.DOWN
        Direction.DOWN -> Direction.LEFT
        Direction.LEFT -> Direction.UP
    }
}

fun getNextStep(
    guard: Pair<Int, Int>,
    direction: Direction,
    matrix: Array<Array<Char>>
): Pair<PositionType, Pair<Int, Int>> {
    val newPosition = when (direction) {
        Direction.UP -> guard.first - 1 to guard.second
        Direction.RIGHT -> guard.first to guard.second + 1
        Direction.DOWN -> guard.first + 1 to guard.second
        Direction.LEFT -> guard.first to guard.second - 1
    }

    return if (isValidIndex(newPosition.first, newPosition.second, matrix)) {
        Pair(PositionType.from(matrix[newPosition.first][newPosition.second]), newPosition)
    } else {
        Pair(PositionType.OUT, 0 to 0)
    }

}

fun solvePart1(input: String): Int {
    val matrix = inputToMatrix(input)
    var guardPosition = findGuardPosition(matrix)
    val visited: MutableSet<Pair<Int, Int>> = mutableSetOf(guardPosition)

    var guardIsInThePremises = true
    var guardDirection = Direction.UP
    while (guardIsInThePremises) {
        val nextStep = getNextStep(guardPosition, guardDirection, matrix)

        if (nextStep.first == PositionType.OUT) {
            guardIsInThePremises = false
        }

        if (nextStep.first == PositionType.BLOCKED) {
            guardDirection = rotateDirection(guardDirection)
        }

        if (nextStep.first == PositionType.FREE) {
            visited.add(nextStep.second)
            guardPosition = nextStep.second
        }
    }


    return visited.size
}