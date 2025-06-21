package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {

    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        boolean isNegative = x < 0;
        long num = ((long) x) * (isNegative ? -1 : 1);
        return Long.parseLong(new StringBuilder(String.valueOf(num)).reverse().toString()) * (isNegative ? -1 : 1);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseDigits.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
