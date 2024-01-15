package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromic {

    @EpiTest(testDataFile = "is_string_palindromic.tsv")
    public static boolean isPalindromic(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
