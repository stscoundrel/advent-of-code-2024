#include <iostream>
#include <fstream>
#include <string>

extern int solvePart1(const std::string& input);

std::string readFileContents(const std::string& filename) {
    std::ifstream testFile(filename, std::ios::in | std::ios::binary);
    const std::string content((std::istreambuf_iterator<char>(testFile)), std::istreambuf_iterator<char>());

    return content;
}

int main() {
    const std::string testInput = readFileContents("test-input.txt");
    const std::string input = readFileContents("input.txt");

    int testResult1 = solvePart1(testInput);
    std::cout << "Result of solvePart1 for test-input : " << testResult1 << std::endl;

    int result1 = solvePart1(input);
    std::cout << "Result of solvePart1 for actual input : " << result1 << std::endl;

    return 0;

}
