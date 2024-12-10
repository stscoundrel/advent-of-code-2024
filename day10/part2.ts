import { getPaths, inputToMatrix } from "./part1"


export const solvePart2 = (input: string): number => {
  let totalScore = 0
  const matrix = inputToMatrix(input)

  matrix.forEach((row, y) => {
    row.forEach((point, x) => {
      if (point === 0) {
        totalScore += getPaths({ x, y }, matrix, 1).length
      }
    })
  })

  return totalScore
}