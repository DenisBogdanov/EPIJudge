package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {

    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        boolean isNegative = false;

        if (x < 0) {
            isNegative = true;
            x = -x;
        }

        long result = 0;

        while (x > 0) {
            result *= 10;
            result += x % 10;

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
