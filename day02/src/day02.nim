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

proc listIsSafeWithDampener(list: seq[int]): bool =
  for idx, number in list.pairs():
    let newList = list[0..idx-1] & list[idx+1..^1]

    if listIsSafe(newList):
      return true

  return false

proc solvePart1(input: string): int =
  let lists = inputToLists(input)  
  for list in lists:
    if listIsSafe(list):
      result += 1

proc solvePart2(input: string): int =
  let lists = inputToLists(input)

  for list in lists:
    if listIsSafe(list):
      result += 1
    elif listIsSafeWithDampener(list):
      result += 1

  
export solvePart1
export solvePart2

when isMainModule:
  echo("Part 1")
  let fileContent = readFile("src/input.txt")

  echo solvePart1(fileContent)
  echo solvePart2(fileContent)
