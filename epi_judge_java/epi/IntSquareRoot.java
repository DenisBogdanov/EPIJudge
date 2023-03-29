package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IntSquareRoot {

    @EpiTest(testDataFile = "int_square_root.tsv")
    public static int squareRoot(int k) {
        long left = 0;
        long right = (long) k + 1;

        while (left + 1 < right) {
            long mid = left + (right - left) / 2;

            long square = mid * mid;
            if (square == k) {
                return (int) mid;
            } else if (square < k) {
                left = mid;
            } else {
                right = mid;
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
