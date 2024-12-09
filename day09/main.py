from part1 import solve_part_1
from part2 import solve_part_2

def main():
    with open("input.txt", 'r') as file:
        input = file.read()

    result1 = solve_part_1(input)
    result2 = solve_part_2(input)
    
    print(f"Part 1: {result1}")
    print(f"Part 2: {result2}")

if __name__ == "__main__":
    main()