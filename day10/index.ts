import * as fs from 'node:fs'
import { solvePart1 } from "./part1"
import { solvePart2 } from "./part2"

const tests = () => {
    const testInput = `89010123
    78121874
    87430965
    96549874
    45678903
    32019012
    01329801
    10456732`

    const testResult1 = solvePart1(testInput)

    console.log(`Part 1 (test): ${testResult1}`)

    if (testResult1 !== 36) {
        console.error(`Part 1 test failed. Received ${testResult1} instead of 36`)
    }

}

const main = async () => {

    const input = fs.readFileSync('input.txt', 'utf8');
    const result1 = solvePart1(input)
    const result2 = solvePart2(input)

    console.log(`Part 1: ${result1}`)
    console.log(`Part 2: ${result2}`)

}

//tests()
main()