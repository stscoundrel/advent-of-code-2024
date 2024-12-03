import org.scalatest.funsuite.AnyFunSuite

class Day03Test extends AnyFunSuite {

  test("solves part 1") {
    val testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    assert(Day03.solvePart1(testInput) == 161)
  }
}
