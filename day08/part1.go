package main

import (
	"fmt"
	"strings"
)

func antennaPositionsFromMatrix(matrix [][]rune, unique_antennas map[rune]bool) map[rune][]Coordinate {
	antenna_positions := make(map[rune][]Coordinate)

	for antenna := range unique_antennas {
		antenna_positions[antenna] = []Coordinate{}
	}

	for i, row := range matrix {
		for j, char := range row {
			if matrix[i][j] != '.' {
				coordinate := Coordinate{X: i, Y: j}
				coordinates := antenna_positions[char]
				coordinates = append(coordinates, coordinate)
				antenna_positions[char] = coordinates
			}
		}
	}

	return antenna_positions
}

func inputToMatrix(input string) ([][]rune, map[rune]bool) {
	lines := strings.Split(input, "\n")
	antennas := make(map[rune]bool)
	matrix := make([][]rune, len(lines))

	for i, line := range lines {
		matrix[i] = []rune(line)
		for _, char := range line {
			antennas[char] = true
		}
	}

	delete(antennas, '.')

	return matrix, antennas
}

func SolvePart1(input string) int {
	antinode_positions := 0
	matrix, unique_antennas := inputToMatrix(input)
	positions := antennaPositionsFromMatrix(matrix, unique_antennas)

	for key := range positions {
		for _, coordinate := range positions[key] {
			// Compare each antenna against each antenna of same type.
			for _, comparison := range positions[key] {
				if coordinate != comparison {
					dx, dy := coordinate.Difference(comparison)
					antiNode := comparison.NewFromDeltas(dx, dy)

					if antiNode.IsInGrid(matrix) {
						matrix[antiNode.X][antiNode.Y] = '#'
					}
				}
			}
		}
	}

	for _, row := range matrix {
		for _, pos := range row {
			if pos == '#' {
				antinode_positions += 1
			}
		}
	}

	for _, row := range matrix {
		fmt.Println(string(row))
	}

	return antinode_positions
}
