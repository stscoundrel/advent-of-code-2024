#include <iostream>
#include <fstream>
#include <string>

extern uint64_t solvePart1(const std::string &input);
extern uint64_t solvePart2(const std::string &input);

std::string readFileContents(const std::string &filename) {
    std::ifstream testFile(filename, std::ios::in | std::ios::binary);
    const std::string content((std::istreambuf_iterator<char>(testFile)), std::istreambuf_iterator<char>());

    return content;
}

int main() {
    const std::string testInput = readFileContents("test-input.txt");
    const std::string input = readFileContents("input.txt");

    uint64_t testResult1 = solvePart1(testInput);
    uint64_t testResult2 = solvePart2(testInput);
    std::cout << "Result of solvePart1 for test-input : " << testResult1 << std::endl;
    std::cout << "Result of solvePart2 for test-input : " << testResult2 << std::endl;

    uint64_t result1 = solvePart1(input);
    uint64_t result2 = solvePart2(input);

    std::cout << "Result of solvePart1 for actual input : " << result1 << std::endl;
    std::cout << "Result of solvePart2 for actual input : " << result2 << std::endl;

    return 0;
}
