package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ReverseDigits {

    @EpiTest(testDataFile = "reverse_digits.tsv")
    public static long reverse(int x) {
        int sign = (x < 0 ? -1 : 1);
        return sign * Long.parseLong(new StringBuilder().append(Math.abs((long) x)).reverse().toString());
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
