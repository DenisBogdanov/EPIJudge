package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class PrimitiveDivide {

    @EpiTest(testDataFile = "primitive_divide.tsv")
    public static int divide(int x, int y) {
        long a = x;
        long b = y;
        int sign = (a * b >= 0 ? 1 : -1);
        a = Math.abs(a);
        b = Math.abs(b);
        long left = 0;
        long right = x + 1;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (mid * b == a) return (int) (sign * mid);
            if (mid * b > a) right = mid;
            else left = mid;
        }
        return (int) (sign * left);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrimitiveDivide.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
