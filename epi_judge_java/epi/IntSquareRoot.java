package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntSquareRoot {

    @EpiTest(testDataFile = "int_square_root.tsv")
    public static int squareRoot(int k) {
        long left = -1;
        long right = k + 1L;

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;

            if (mid * mid > k) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return (int) left;
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
