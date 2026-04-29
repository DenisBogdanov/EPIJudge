package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPermutableToPalindrome {

    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
    public static boolean canFormPalindrome(String s) {
        char[] chars = new char[256];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)]++;
        }
        int oddCount = 0;
        for (int count : chars) {
            if (count % 2 != 0) oddCount++;
            if (oddCount == 2) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
