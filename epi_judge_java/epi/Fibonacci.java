package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Fibonacci {

    @EpiTest(testDataFile = "fibonacci.tsv")
    public static int fibonacci(int n) {
        if (n < 2) return n;
        int a = 0;
        int b = 1;

        for (int i = 2; i <= n; i++) {
            int temp = a;
            a = b;
            b += temp;
        }

        return b;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Fibonacci.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
