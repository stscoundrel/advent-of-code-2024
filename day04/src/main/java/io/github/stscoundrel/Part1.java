package io.github.stscoundrel;

import java.util.ArrayList;
import java.util.List;

public class Part1 {
    private int results = 0;

    private char[][] inputToMatrix(String input) {
        return input.lines()
                .map(line -> line.toCharArray())
                .toArray(char[][]::new);
    }

    private String coordinatesToWord(List<Coordinate> coordinates, char[][] matrix) {
        StringBuilder word = new StringBuilder();
        for (Coordinate coordinate : coordinates) {
            char letter = matrix[coordinate.i][coordinate.j];
            word.append(letter);
        }

        return word.toString();
    }

    private void findWordsStartingFrom(int i, int j, char[][] matrix, char[] wordChars, String word) {
        // Right.
        if ((j + wordChars.length) <= matrix[j].length) {
            List<Coordinate> rightCoordinates = new ArrayList<>();
            for (int k = j; k < (wordChars.length + j); k++) {
                rightCoordinates.add(new Coordinate(i, k));
            }

            if (coordinatesToWord(rightCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }

        // Left.
        if (j >= wordChars.length - 1) {
            List<Coordinate> leftCoordinates = new ArrayList<>();
            for (int k = j; k > (j - word.length()); k--) {
                leftCoordinates.add(new Coordinate(i, k));
            }
            if (coordinatesToWord(leftCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }

        // Up.
        if ((i - wordChars.length + 1) >= 0) {
            List<Coordinate> upCoordinates = new ArrayList<>();
            for (int k = i; k > (i - wordChars.length); k--) {
                upCoordinates.add(new Coordinate(k, j));
            }
            if (coordinatesToWord(upCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }

        // Down.
        if ((i + wordChars.length) <= matrix.length) {
            List<Coordinate> downCoordinates = new ArrayList<>();
            for (int k = i; k < i + wordChars.length; k++) {
                downCoordinates.add(new Coordinate(k, j));
            }
            if (coordinatesToWord(downCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }

        // Diagonal down right.
        if ((j + wordChars.length) <= matrix[j].length && (i + wordChars.length) <= matrix.length) {
            List<Coordinate> diagonalRightCoordinates = new ArrayList<>();
            int startI = i;
            int startJ = j;
            for (int k = 0; k < wordChars.length; k++) {
                diagonalRightCoordinates.add(new Coordinate(startI, startJ));
                startI++;
                startJ++;
            }
            if (coordinatesToWord(diagonalRightCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }

        // Diagonal up right.
        if ((i - wordChars.length + 1) >= 0 && (j + wordChars.length) <= matrix[j].length) {
            List<Coordinate> diagonalUpRightCoordinates = new ArrayList<>();
            int startI = i;
            int startJ = j;
            for (int k = 0; k < wordChars.length; k++) {
                diagonalUpRightCoordinates.add(new Coordinate(startI, startJ));
                startI--;
                startJ++;
            }
            if (coordinatesToWord(diagonalUpRightCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }

        // Diagonal up left.
        if ((i - wordChars.length + 1) >= 0 && j >= wordChars.length - 1) {
            List<Coordinate> diagonalUpLeftCoordinates = new ArrayList<>();
            int startI = i;
            int startJ = j;
            for (int k = 0; k < wordChars.length; k++) {
                diagonalUpLeftCoordinates.add(new Coordinate(startI, startJ));
                startI--;
                startJ--;
            }
            if (coordinatesToWord(diagonalUpLeftCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }

        // Diagonal down left.
        if (j >= wordChars.length - 1 && (i + wordChars.length) <= matrix.length) {
            List<Coordinate> diagonalLeftCoordinates = new ArrayList<>();
            int startI = i;
            int startJ = j;
            for (int k = 0; k < wordChars.length; k++) {
                diagonalLeftCoordinates.add(new Coordinate(startI, startJ));
                startI++;
                startJ--;
            }
            if (coordinatesToWord(diagonalLeftCoordinates, matrix).equals(word)) {
                results += 1;
            }
        }
    }

    public int solvePart1(String wordInput, String input) {
        char[] word = wordInput.toCharArray();
        var matrix = inputToMatrix(input);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == word[0]) {
                    findWordsStartingFrom(i, j, matrix, word, wordInput);
                }
            }
        }

        return results;
    }
}
