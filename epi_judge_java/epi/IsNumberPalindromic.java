package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {

    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber(int x) {
        if (x < 0) return false;
        int len = (int) Math.log10(x) + 1;
        int pow = 1;
        for (int i = 1; i < len; i++) {
            pow *= 10;
        }

        for (int i = 0; i < len / 2; i++) {
            int left = x / pow % 10;
            int right = x % 10;
            x %= pow;
            x /= 10;
            if (left != right) return false;
            pow /= 100;
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
