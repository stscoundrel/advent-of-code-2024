int solveDay01(List<int> list1, List<int> list2) {
  int differences = 0;

  List<int> sortedList1 = List.from(list1)..sort();
  List<int> sortedList2 = List.from(list2)..sort();

  for (int i = 0; i < sortedList1.length; i++) {
    int difference = (sortedList1[i] - sortedList2[i]).abs();
    differences += difference;
  }

  return differences;
}
