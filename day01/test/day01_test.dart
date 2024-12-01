import 'package:day01/day01.dart';
import 'package:test/test.dart';

void main() {
  test('Solves part 1', () {
    const List<int> list1 = [3, 4, 2, 1, 3, 3];
    const List<int> list2 = [4, 3, 5, 3, 9, 3];

    expect(solvePart1(list1, list2), 11);
  });

  test('Solves part 2', () {
    const List<int> list1 = [3, 4, 2, 1, 3, 3];
    const List<int> list2 = [4, 3, 5, 3, 9, 3];

    expect(solvePart2(list1, list2), 31);
  });
}
