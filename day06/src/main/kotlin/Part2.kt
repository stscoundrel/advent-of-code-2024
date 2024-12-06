fun getPossibleBlockCoordinates(matrix: Array<Array<Char>>, guardPosition: Pair<Int, Int>): Set<Pair<Int, Int>> {
    val possiblePositions: MutableSet<Pair<Int, Int>> = mutableSetOf()
    matrix.forEachIndexed { index1, row ->
        row.forEachIndexed { index2, column ->
            if (PositionType.from(column) == PositionType.FREE) {
                // ... But not guard position.
                if (Pair(index1, index2) !== guardPosition) {
                    possiblePositions.add(Pair(index1, index2))
                }
            }
        }
    }

    return possiblePositions
}

fun matrixResultsInLoop(matrix: Array<Array<Char>>, initialGuardPosition: Pair<Int, Int>): Boolean {
    var guardPosition = initialGuardPosition
    val turns: MutableList<Pair<Int, Int>> = mutableListOf()

    var guardIsInThePremises = true
    var guardDirection = Direction.UP
    while (guardIsInThePremises) {
        val nextStep = getNextStep(guardPosition, guardDirection, matrix)

        if (nextStep.first == PositionType.OUT) {
            guardIsInThePremises = false
        }

        if (nextStep.first == PositionType.BLOCKED) {
            // If we've made the turn before, it's a loop.
            if (turns.contains(guardPosition) && turns.last() != guardPosition) {
                return true
            }

            guardDirection = rotateDirection(guardDirection)
            turns.add(guardPosition)
        }

        if (nextStep.first == PositionType.FREE) {
            guardPosition = nextStep.second
        }
    }

    return false
}

fun solvePart2(input: String): Int {
    val matrix = inputToMatrix(input)
    val guardPosition = findGuardPosition(matrix)
    val potentialBlocks = getPossibleBlockCoordinates(matrix, guardPosition)

    var infiniteLoops = 0

    // Alter matrix.
    potentialBlocks.forEach { block ->
        val newMatrix = matrix.map { it.clone() }.toTypedArray()
        newMatrix[block.first][block.second] = '#'

        if (matrixResultsInLoop(newMatrix, guardPosition)) {
            infiniteLoops++
        }
    }


    return infiniteLoops
}