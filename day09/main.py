from part1 import solve_part_1

def main():
    with open("input.txt", 'r') as file:
        input = file.read()

    result1 = solve_part_1(input)
    print(f"Part 1: {result1}")

if __name__ == "__main__":
    main()