package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class LevenshteinDistance {
    private static final int INF = 1_000_000_000;

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinDistance(String a, String b) {
        int n1 = a.length();
        int n2 = b.length();

        int[][] size1ToSize2ToMinDistDp = new int[n1][n2];
        for (int[] row : size1ToSize2ToMinDistDp) {
            Arrays.fill(row, INF);
        }

//        for (int i = 1; i <= n1; i++) {
//            for (int j = 1; j <= n2; j++) {
//                size1ToSize2ToMinDistDp[i][j] = size1ToSize2ToMinDistDp[i][j - 1];
//                if (a.charAt(i - 1) != b.charAt(i - 1)) {
//
//                }
//            }
//        }
//
//        return size1ToSize2ToMinDistDp[n1][n2];

        return helper(a, 0, b, 0, size1ToSize2ToMinDistDp);
    }

    private static int helper(String a, int i1, String b, int i2, int[][] dp) {
        int n1 = a.length();
        int n2 = b.length();

        if (i1 == n1) return n2 - i2;
        if (i2 == n2) return n1 - i1;

        if (dp[i1][i2] != INF) return dp[i1][i2];

        return dp[i1][i2] = Math.min(
                helper(a, i1 + 1, b, i2 + 1, dp) + (a.charAt(i1) == b.charAt(i2) ? 0 : 1),
                1 + Math.min(helper(a, i1 + 1, b, i2, dp),
                        helper(a, i1, b, i2 + 1, dp)));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
