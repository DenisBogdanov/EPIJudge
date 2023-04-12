package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {
    private static final int PRIME = 31;

    @EpiTest(testDataFile = "substring_match.tsv")
    // Returns the index of the first character of the substring if found, -1
    // otherwise.
    public static int rabinKarp(String t, String s) {
        int sLen = s.length();
        if (sLen > t.length()) return -1;

        long currHash = 0;
        long subHash = 0;

        long pow = 1;

        for (int i = 0; i < sLen; i++) {
            currHash *= PRIME;
            currHash += t.charAt(i);

            subHash *= PRIME;
            subHash += s.charAt(i);

            pow *= PRIME;
        }

        if (currHash == subHash && t.startsWith(s)) {
            return 0;
        }

        for (int i = sLen; i < t.length(); i++) {
            currHash *= PRIME;
            currHash += t.charAt(i);
            currHash -= pow * t.charAt(i - sLen);

            if (currHash == subHash && equals(t, i - sLen + 1, s)) {
                return i - sLen + 1;
            }
        }

        return -1;
    }

    private static boolean equals(String t, int start, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (t.charAt(start + i) != s.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SubstringMatch.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
