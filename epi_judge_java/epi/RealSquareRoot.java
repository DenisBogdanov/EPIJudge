package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RealSquareRoot {

    @EpiTest(testDataFile = "real_square_root.tsv")
    public static double squareRoot(double x) {
        double left = -1;
        double right = x + 1;

        while (right - left > 1e-8) {
            double mid = (left + right) / 2;
            if (mid * mid > x) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return left;
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
