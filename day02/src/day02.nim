import std/strutils
import std/sequtils

proc inputToLists(input: string): seq[seq[int]] =
  result = input
    .splitLines()
    .map(proc(line: string): seq[int] =
      line.splitWhitespace().map(parseInt))

proc listIsSafe(list: seq[int]): bool =
  let isIncreasing = list[0] < list[1]
  var prev = list[0]
  for number in list[1..^1]:
    let difference = prev - number

    if isIncreasing:   
      if difference > -1 or difference < -3:
        return false
    
    else:  
      if difference < 1 or difference > 3:
        return false

    prev = number

  return true

proc solvePart1(input: string): int =
  let lists = inputToLists(input)  
  for list in lists:
    if listIsSafe(list):
      result += 1
  
export solvePart1

when isMainModule:
  echo("Part 1")
  let fileContent = readFile("src/input.txt")

  echo solvePart1(fileContent)
