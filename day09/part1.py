def parse_input(input: str) -> list[int | None]:
    id = 0
    result = []

    for idx, str_number in enumerate(input):
        number = int(str_number)
        value = None if idx % 2 != 0 else id
        
        for number in range(1, number + 1):
            result.append(value)
        
        if value is None:
            id += 1

    return result

def optimize(input: list[int | None]) -> list[int | None]:
    result = list(input)
    pos = -1
    lastFreePosition = 0

    for number in reversed(input):
        if number != None:
            for idx, char in enumerate(result[lastFreePosition:pos]):
                if char is None:
                    result[lastFreePosition + idx] = number
                    result[pos] = None
                    lastFreePosition = idx       

                    break
        
        pos -= 1

    return result

def calculate_checksum(input: list[int | None]) -> int:
    return sum(
        [
            idx * number
            for idx, number in enumerate(input)
            if number is not None       
        ]
    )


def solve_part_1(input: str) -> int:
    parsed = parse_input(input)
    optimized = optimize(parsed)
    checksum = calculate_checksum(optimized)

    return checksum