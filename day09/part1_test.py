from unittest import TestCase, main
from part1 import solve_part_1

class TestSolvePart1(TestCase):

    def test_solves_part_1(self):
        test_input = "2333133121414131402"
        expected_output = 1928 
        self.assertEqual(solve_part_1(test_input), expected_output)

if __name__ == "__main__":
    main()