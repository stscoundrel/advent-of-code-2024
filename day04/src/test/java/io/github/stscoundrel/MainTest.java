package io.github.stscoundrel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void solvesPart1() {
        var app = new Part1();
        final String testInput = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX""";

        assertEquals(app.solvePart1("XMAS", testInput), 18);
    }

    @Test
    void solvesPart2() {
        var app = new Part2();
        final String testInput = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX""";

        assertEquals(app.solvePart2(testInput), 9);
    }
}