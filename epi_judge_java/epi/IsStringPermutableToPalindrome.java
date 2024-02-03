package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPermutableToPalindrome {

    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
    public static boolean canFormPalindrome(String s) {
        int counts = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            counts ^= (1 << (c - 'a'));
        }

        return counts == 0 || ((counts & (counts - 1)) == 0);
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
