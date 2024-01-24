package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class BinomialCoefficients {

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficient(int n, int k) {
        if (k == 0) return 1;

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        return dp[n][k];
    }

    private static class Pair {
        final int n;
        final int k;

        public Pair(int n, int k) {
            this.n = n;
            this.k = k;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BinomialCoefficients.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
