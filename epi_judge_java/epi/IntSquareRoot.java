package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntSquareRoot {

  @EpiTest(testDataFile = "int_square_root.tsv")
  public static int squareRoot(int k) {
    int left = 0;
    int right = k;

    while (left < right) {
      int mid = left + (right - left) / 2;
      long square = (long) mid * mid;

      if (square == k) {
        return mid;
      } else if (square < k) {
        if (square + 2L * mid + 1 > k) {
          return mid;
        }

        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
