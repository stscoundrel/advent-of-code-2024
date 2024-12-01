int solvePart1(List<int> list1, List<int> list2) {
  int differences = 0;

  List<int> sortedList1 = List.from(list1)..sort();
  List<int> sortedList2 = List.from(list2)..sort();

  for (int i = 0; i < sortedList1.length; i++) {
    int difference = (sortedList1[i] - sortedList2[i]).abs();
    differences += difference;
  }

  return differences;
}

int solvePart2(List<int> list1, List<int> list2) {
  int result = 0;

  // Build map of counts in right list.
  Map<int, int> rightAppearances = {};
  for (int i = 0; i < list2.length; i++) {
    int key = list2[i];
    rightAppearances.putIfAbsent(key, () => 0);

    rightAppearances[key] = rightAppearances[key]! + 1;
  }

  // Go through left list, calculate by right list.
  for (int i = 0; i < list1.length; i++) {
    int key = list1[i];
    if (rightAppearances.containsKey(key)) {
      result += (key * rightAppearances[key]!);
    }
  }

  return result;
}
