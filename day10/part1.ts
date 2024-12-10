interface Coordinate {
  x: number,
  y: number,
}

const padMatrix = (input: number[][]): number[][] => {
  const fullRow = Array(input[0].length + 2).fill(666);
  return [
    fullRow,
    ...input.map(row => [666, ...row, 666]),
    fullRow
  ]
}

export const inputToMatrix = (input: string): number[][] => padMatrix(input
  .split('\n')
  .map((line) => line.split('').map((number) => Number(number))))

const getPotentials = (point: Coordinate): Coordinate[] => {
  return [
    { x: point.x, y: point.y - 1 }, // Up.
    { x: point.x, y: point.y + 1 }, // Down.
    { x: point.x - 1, y: point.y }, // Left
    { x: point.x + 1, y: point.y }, // Right
  ]
}

export const getPaths = (point: Coordinate, matrix: number[][], next: number) => {
  const potentials = getPotentials(point)

  const pathResults = potentials
    .map((potential) => ({ value: matrix[potential.y][potential.x], point: potential }))
    .filter((potential) => potential.value === next)
    .map((potential) => next < 9 ? getPaths(potential.point, matrix, next + 1) : [potential.point])
    .flat()

  return pathResults;
}

const scoreTrails = (trailEnds: Coordinate[]): number => {
  // Fuck my reading comprehension.
  const uniques = new Set()
  trailEnds.forEach((trailEnd) => uniques.add(`${trailEnd.x}-${trailEnd.y}`))

  return uniques.size

}

export const solvePart1 = (input: string): number => {
  let totalScore = 0
  const matrix = inputToMatrix(input)

  matrix.forEach((row, y) => {
    row.forEach((point, x) => {
      if (point === 0) {
        const newTrails = getPaths({ x, y }, matrix, 1)
        totalScore += scoreTrails(newTrails)
      }
    })
  })

  return totalScore
}