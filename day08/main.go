package main

import (
	"fmt"
	"io/ioutil"
	"log"
)

func readInput(filename string) string {
	content, err := ioutil.ReadFile(filename)
	if err != nil {
		log.Fatalf("Failed to read file %s: %v", filename, err)
	}
	return string(content)

}

func main() {
	input := readInput("input.txt")
	part1 := SolvePart1(input)
	fmt.Printf("Part 1: %d\n", part1)
}
