package main

import "fmt"

type Coordinate struct {
	X int
	Y int
}

func (c1 Coordinate) Difference(c2 Coordinate) (dx int, dy int) {
	dx = c2.X - c1.X
	dy = c2.Y - c1.Y
	return
}

func (c Coordinate) NewFromDeltas(dx, dy int) Coordinate {
	return Coordinate{X: c.X + dx, Y: c.Y + dy}
}

func (c Coordinate) IsInGrid(grid [][]rune) bool {
	if c.X >= 0 && c.X < len(grid) && c.Y >= 0 && c.Y < len(grid[0]) {
		return true
	}
	return false
}

func (c Coordinate) String() string {
	return fmt.Sprintf("Coordinate(X: %d, Y: %d)", c.X, c.Y)
}
