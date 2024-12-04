package io.github.stscoundrel;

public class Part2 {
    public int results = 0;

    private char[][] inputToMatrix(String input) {
        return input.lines()
                .map(line -> line.toCharArray())
                .toArray(char[][]::new);
    }

    private void findTheFuckingXMas(int i, int j, char[][] matrix) {
        var upLeft = matrix[i - 1][j - 1];
        var upRight = matrix[i - 1][j + 1];
        var downLeft = matrix[i + 1][j - 1];
        var downRight = matrix[i + 1][j + 1];
        /*
         * M S
         *  A
         * M S
         */
        if (upLeft == 'M' && upRight == 'S' && downLeft == 'M' && downRight == 'S') {
            results++;
        }

        /*
         * S S
         *  A
         * M M
         */
        if (upLeft == 'S' && upRight == 'S' && downLeft == 'M' && downRight == 'M') {
            results++;
        }

        /*
         * M M
         *  A
         * S S
         */
        if (upLeft == 'M' && upRight == 'M' && downLeft == 'S' && downRight == 'S') {
            results++;
        }

        /*
         * S M
         *  A
         * S M
         */
        if (upLeft == 'S' && upRight == 'M' && downLeft == 'S' && downRight == 'M') {
            results++;
        }

    }

    public int solvePart2(String input) {
        var matrix = inputToMatrix(input);

        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] == 'A') {
                    findTheFuckingXMas(i, j, matrix);
                }
            }
        }

        return results;
    }
}
