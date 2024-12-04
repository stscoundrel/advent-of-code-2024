package io.github.stscoundrel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("input.txt");
        String content = Files.readString(filePath);

        var part1 = new Part1();
        int part1Result = part1.solvePart1("XMAS", content);

        System.out.println(part1Result);
    }
}