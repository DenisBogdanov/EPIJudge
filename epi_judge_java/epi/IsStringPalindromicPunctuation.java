package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromicPunctuation {

    @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !(Character.isAlphabetic(s.charAt(left)) || Character.isDigit(s.charAt(left)))) {
                left++;
            }

            while (left < right && !(Character.isAlphabetic(s.charAt(right)) || Character.isDigit(s.charAt(right)))) {
                right--;
            }

            if (left == right) break;

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
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
                        .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
