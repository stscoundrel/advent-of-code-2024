using Day05;

var directory = AppDomain.CurrentDomain.BaseDirectory;
var filePath = Path.Combine(directory, "input.txt");

var input = File.ReadAllText(filePath);

Console.WriteLine("Part 1: " + Part1.SolvePart1(input));