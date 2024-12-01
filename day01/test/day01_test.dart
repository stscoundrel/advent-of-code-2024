import 'package:day01/day01.dart';
import 'package:test/test.dart';

void main() {
  test('Sums distances of matching numbers', () {
    const List<int> list1 = [3, 4, 2, 1, 3, 3];
    const List<int> list2 = [4, 3, 5, 3, 9, 3];

    expect(solveDay01(list1, list2), 11);
  });
}
