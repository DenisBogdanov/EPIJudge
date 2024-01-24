package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LevenshteinDistance {
    private static final int INF = 1_000_000_000;

    @EpiTest(testDataFile = "levenshtein_distance.tsv")
    public static int levenshteinDistance(String a, String b) {
        int n1 = a.length();
        int n2 = b.length();

        int[][] dp = new int[n1][n2];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                dp[i][j] = INF;
            }
        }

        return helper(a, 0, b, 0, dp);
    }

    private static int helper(String a, int i, String b, int j, int[][] dp) {
        if (a.length() == i) return b.length() - j;
        if (b.length() == j) return a.length() - i;

        if (dp[i][j] != INF) return dp[i][j];

        if (a.charAt(i) == b.charAt(j)) {
            return dp[i][j] = helper(a, i + 1, b, j + 1, dp);
        } else {
            return dp[i][j] = 1 + Math.min(
                    helper(a, i + 1, b, j + 1, dp),
                    Math.min(
                            helper(a, i, b, j + 1, dp),
                            helper(a, i + 1, b, j, dp))
            );
        }
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
