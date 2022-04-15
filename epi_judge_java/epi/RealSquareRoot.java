package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RealSquareRoot {

  @EpiTest(testDataFile = "real_square_root.tsv")
  public static double squareRoot(double x) {
    double left;
    double right;

    if (x < 1.0) {
      left = 0.0;
      right = 1.0;
    } else {
      left = 1.0;
      right = x;
    }

    while (Math.abs(right - left) > 1e-8) {
      double mid = left + (right - left) / 2;
      double square = mid * mid;

      if (square > x) {
        right = mid;
      } else {
        left = mid;
      }
    }

    return Math.max(left, right);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RealSquareRoot.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
