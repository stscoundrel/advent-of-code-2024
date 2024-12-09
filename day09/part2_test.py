from unittest import TestCase, main
from part2 import solve_part_2

class TestSolvePart2(TestCase):

    def test_solves_part_2(self):
        test_input = "2333133121414131402"
        expected_output = 2858 
        self.assertEqual(solve_part_2(test_input), expected_output)

if __name__ == "__main__":
    main()