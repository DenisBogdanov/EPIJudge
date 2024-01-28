package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {

    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        boolean isNegative = x < 0;
        x = Math.abs(x);

        long result = 0;
        int len = (int) Math.log10(x) + 1;

        for (int i = 0; i < len; i++) {
            int digit = x % 10;
            result *= 10;
            result += digit;
            x /= 10;
        }

        return isNegative ? -result : result;
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
