package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {
    private static final int PRIME = 37;

    @EpiTest(testDataFile = "substring_match.tsv")
    // Returns the index of the first character of the substring if found, -1
    // otherwise.
    public static int rabinKarp(String t, String s) {
        if (t.length() < s.length()) return -1;
        long pow = 1;
        long tHash = 0;
        long sHash = 0;

        int n = s.length();

        for (int i = 0; i < n; i++) {
            tHash = tHash * PRIME + t.charAt(i);
            sHash = sHash * PRIME + s.charAt(i);

            if (i != 0) {
                pow *= PRIME;
            }
        }

        if (sHash == tHash && eq(t, 0, s)) {
            return 0;
        }

        for (int i = 1; i <= t.length() - n; i++) {
            tHash -= pow * t.charAt(i - 1);
            tHash = tHash * PRIME + t.charAt(i + n - 1);

            if (sHash == tHash && eq(t, i, s)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean eq(String t, int left, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (t.charAt(i + left) != s.charAt(i)) return false;
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
