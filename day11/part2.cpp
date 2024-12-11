#include <iostream>
#include <string>

#include <sstream>
#include <map>

extern bool hasEvenDigits(uint64_t num);
extern std::pair<uint64_t, uint64_t> splitNumber(uint64_t num);

std::map<uint64_t, uint64_t> parseNumbersMap(const std::string &input) {
    std::map<uint64_t, uint64_t> numbersMap;
    std::istringstream stream(input);
    uint64_t number;

    while (stream >> number) {
        numbersMap[number] = 1;
    }

    return numbersMap;
}

std::map<uint64_t, uint64_t> blick(std::map<uint64_t, uint64_t> numbers) {
    std::map result(numbers);

    for (const auto &pair: numbers) {
        if (pair.second == 0) {
            continue;
        }
        if (pair.first == 0) {
            result[1] += pair.second;
            result[0] -= pair.second;
            continue;
        }
        if (hasEvenDigits(pair.first)) {
            std::pair split = splitNumber(pair.first);
            result[split.first] += pair.second;
            result[split.second] += pair.second;
            result[pair.first] -= pair.second;
            continue;
        }

        result[pair.first * 2024] += pair.second;
        result[pair.first] -= pair.second;
    }

    return result;
}

uint64_t sumNumberMap(std::map<uint64_t, uint64_t> numbers) {
    uint64_t sum = 0;

    for (const auto &pair: numbers) {
        sum += pair.second;
    }

    return sum;
}


uint64_t solvePart2(const std::string &input) {
    std::map<uint64_t, uint64_t> numbers = parseNumbersMap(input);

    for (int i = 0; i < 75; ++i) {
        numbers = blick(numbers);
    }

    return sumNumberMap(numbers);;
}
