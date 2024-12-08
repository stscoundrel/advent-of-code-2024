package main

import (
	"testing"
)

const testInput = `............
........0...
.....0......
.......0....
....0.......
......A.....
............
............
........A...
.........A..
............
............`

func TestSolvesPart1(t *testing.T) {

	result := SolvePart1(testInput)
	expected := 14

	if result != expected {
		t.Errorf("Part 1 failed. Received %d; expected %d", result, expected)
	}
}

func TestSolvesPart2(t *testing.T) {
	result := SolvePart2(testInput)
	expected := 34

	if result != expected {
		t.Errorf("Part 2 failed. Received %d; expected %d", result, expected)
	}
}
