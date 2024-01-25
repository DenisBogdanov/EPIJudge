package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Gcd {

    @EpiTest(testDataFile = "gcd.tsv")
    public static long GCD(long x, long y) {
        while (y != 0) {
            long temp = y;
            y = x % y;
            x = temp;
        }

        return x;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Gcd.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
