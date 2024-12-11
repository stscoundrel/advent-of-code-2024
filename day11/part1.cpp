#include <iostream>
#include <string>

#include <sstream>
#include <vector>

template <typename T>
void printNumbers(const std::vector<T>& numbers) {
    for (size_t i = 0; i < numbers.size(); ++i) {
        std::cout << numbers[i];
        if (i != numbers.size() - 1) {
            std::cout << " ";
        }
    }
    std::cout << std::endl;
}

std::vector<uint64_t> parseNumbers(const std::string& input) {
    std::vector<uint64_t> numbers;
    std::istringstream stream(input);
    uint64_t number;

    while (stream >> number) {
        numbers.push_back(number);
    }

    return numbers;
}

bool hasEvenDigits(uint64_t num) {
    std::string numStr = std::to_string(num);
    return numStr.length() % 2 == 0;
}

std::pair<uint64_t, uint64_t> splitNumber(uint64_t num) {
    std::string numStr = std::to_string(num);
    int splitPoint = numStr.length() / 2;

    std::string first = numStr.substr(0, splitPoint);
    std::string second = numStr.substr(splitPoint);

    uint64_t firstNum = std::stoull(first);
    uint64_t secondNum = std::stoull(second);

    return {firstNum, secondNum};

}

std::vector<uint64_t> blick(std::vector<uint64_t> numbers) {
    std::vector<uint64_t> result;

    for (uint64_t num : numbers) {
        if (num == 0) {
            result.push_back(1);
            continue;
        }
        if (hasEvenDigits(num)) {
            std::pair split = splitNumber(num);
            result.push_back(split.first);
            result.push_back(split.second);
            continue;
        }

        result.push_back(num * 2024);
    }

    return result;
}

uint64_t solvePart1(const std::string& input) {
    std::vector<uint64_t> numbers = parseNumbers(input);

    for (int i = 0; i < 25; ++i) {
        // printNumbers(numbers);
        numbers = blick(numbers);
    }

    return numbers.size();
}