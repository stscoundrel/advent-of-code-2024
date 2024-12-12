const testInput = `RRRRIICCFF
RRRRIICCCF
VVRRRCCFFF
VVRCCCJFFF
VVVVCJJCFE
VVIVCCJJEE
VVIIICJJEE
MIIIIIJJEE
MIIISIJEEE
MMMISSJEEE`

const inputToMatrix = (input) => {
  return input
    .split('\n')
    .map(line => line.split(""))
}

const coordString = (x, y) => `${x}-${y}`

const isInMatrix = (point, matrix) => {
    if (point.x < 0 || point.x >= matrix.length) return false
    if (point.y < 0 || point.y >= matrix[0].length) return false
    return true;
}

const getNeighbours = (x, y, matrix, returnOutOfBounds = false) => {
  const all = [
    { x: x, y: y - 1 }, // Up.
    { x: x, y: y + 1 }, // Down.
    { x: x - 1, y: y }, // Left
    { x: x + 1, y: y }, // Right
  ]
  
  if (returnOutOfBounds) return all.map(point => isInMatrix(point, matrix) ? point : {x: 666, y: 666})
  
  return all.filter(point => isInMatrix(point, matrix))
}

const findPlot = (x, y, plot, matrix, plotHashes) => {
  let updatedPlot = plot
  const plotType = matrix[y][x]
  const potentialNeighbours = getNeighbours(x, y, matrix)
  
  potentialNeighbours.forEach(neighbour => {
    if(matrix[neighbour.y][neighbour.x] == plotType && !plotHashes.includes(coordString(neighbour.x, neighbour.y))) {
      plotHashes.push(coordString(neighbour.x, neighbour.y))
      plot.push(neighbour)
      updatedPlot = findPlot(neighbour.x, neighbour.y, updatedPlot, matrix, plotHashes)
    }
  })
  
  return updatedPlot
}

const isFenceNeighbour = (plotType, point, matrix) => {
  if(point.x === 666) {
    // Hail Satan!
    return true
  }
  
  return matrix[point.y][point.x] !== plotType
}

const getPerimeter = (plot, matrix) => {
  const plotType = matrix[plot[0].y][plot[0].x]
  return plot
    .map(tile => getNeighbours(tile.x, tile.y, matrix, true))
    .flat()
    .filter(tile => isFenceNeighbour(plotType, tile, matrix))
    .length
    
}

const calculatePrice = (plot, matrix) => plot.length * getPerimeter(plot, matrix)


const matrixToPlots = (matrix) => {
  const visited = []
  const plots = []
  
  matrix.forEach((row, y) => {
    row.forEach((point, x) => {
      if(!visited.includes(coordString(x, y))) {
        const plot = findPlot(x, y, [{x, y}], matrix, [coordString(x, y)])
        plots.push(plot)
        
        plot.forEach(point => visited.push(coordString(point.x, point.y)))
      }
    })
  })
  
  return plots
}

const solvePart1 = () => {
  const matrix = inputToMatrix(testInput)
  const plots = matrixToPlots(matrix)
  
  const sum = plots
    .map(plot => calculatePrice(plot, matrix))
    .reduce((accumulator, current) => accumulator + current, 0);
    
  console.log(sum)
}

solvePart1()