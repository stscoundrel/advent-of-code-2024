import 'package:day01/day01.dart' as day01;

import '../lib/day01.dart';

const String input = """UNIQUE INPUT HERE
""";

List<List<int>> getTestInputAsLists(String testInput) {
  List<String> lines = testInput.split('\n');

  List<int> firstColumn = [];
  List<int> secondColumn = [];

  for (var line in lines) {
    var columns = line.split('   ').map((e) => e.trim()).toList();
    if (columns.length == 2) {
      firstColumn.add(int.parse(columns[0]));
      secondColumn.add(int.parse(columns[1]));
    }
  }

  return [firstColumn, secondColumn];
}

void main(List<String> arguments) {
  var inputLists = getTestInputAsLists(input);
  int result1 = solvePart1(inputLists[0], inputLists[1]);

  print("Part 1:");
  print(result1);

  int result2 = solvePart2(inputLists[0], inputLists[1]);

  print("Part 2:");
  print(result2);
}
