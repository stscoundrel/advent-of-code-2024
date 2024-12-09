def parse_input(input: str) -> list[list[int | None]]:
    id = 0
    result = []

    for idx, str_number in enumerate(input):
        number = int(str_number)
        value = None if idx % 2 != 0 else id
        values_list = []
        for number in range(1, number + 1):
            values_list.append(value)
        
        if value is None:
            id += 1

        if values_list:
            result.append(values_list)

    return result

def optimize(input: list[list[int | None]]) -> list[int | None]:
    result = input
    pos = -1

    for group in reversed(input):
        nones = group.count(None)
        if nones < len(group):
            for potentialIdx, potentialGroup in enumerate(result[0:pos]):
                spaces = potentialGroup.count(None)
                if spaces >= len([item for item in group if item is not None]):
                    for numberToPlaceIdx, numberToPlace in enumerate(group):
                        if numberToPlace is not None:
                            for potentialSpaceIdx, potentialSpace in enumerate(potentialGroup):
                                if potentialSpace is None:
                                    result[potentialIdx][potentialSpaceIdx] = numberToPlace
                                    result[pos][numberToPlaceIdx] = None
                                    break                            
                    break
        pos -= 1

    return [
        item
        for item_list in result
        for item in item_list
    ]

def calculate_checksum(input: list[int | None]) -> int:
    return sum(
        [
            idx * number
            for idx, number in enumerate(input)
            if number is not None       
        ]
    )


def solve_part_2(input: str) -> int:
    parsed = parse_input(input)
    optimized = optimize(parsed)
    checksum = calculate_checksum(optimized)

    return checksum