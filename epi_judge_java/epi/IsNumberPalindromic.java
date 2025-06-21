package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {

    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber(int x) {
        if (x < 0) {
            return false;
        }

        return x == ReverseDigits.reverse(x);
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
