package io.github.stscoundrel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {


    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("input.txt");
        String content = Files.readString(filePath);

        var part1 = new Part1();
        int part1Result = part1.solvePart1("XMAS", content);

        var part2 = new Part2();
        int part2Result = part2.solvePart2(content);

        System.out.println(part1Result);
        System.out.println(part2Result);
    }
}