package main

import (
	"fmt"
)

func SolvePart2(input string) int {
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
						neverStopTheMadness := true

						baseAntiNode := antiNode

						// Kohta vedetään taas.
						for neverStopTheMadness {
							newAntiNode := baseAntiNode.NewFromDeltas(dx, dy)
							if newAntiNode.IsInGrid(matrix) {
								matrix[newAntiNode.X][newAntiNode.Y] = '#'
								baseAntiNode = newAntiNode
							} else {
								neverStopTheMadness = false
							}
						}
					}
				}
			}
		}
	}

	for _, row := range matrix {
		for _, pos := range row {
			if pos != '.' {
				antinode_positions += 1
			}
		}
	}

	for _, row := range matrix {
		fmt.Println(string(row))
	}

	return antinode_positions
}
