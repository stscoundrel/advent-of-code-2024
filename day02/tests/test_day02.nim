import unittest
import ../src/day02


suite "Solves Day02":
    const testInput = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9"""

    test "Solves part 1":       
        check(day02.solvePart1(testInput) == 2)

    test "Solves part 2":
      check(day02.solvePart2(testInput) == 4)