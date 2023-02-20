package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class BinomialCoefficients {

    @EpiTest(testDataFile = "binomial_coefficients.tsv")
    public static int computeBinomialCoefficient(int n, int k) {
        if (n == k || n == 0 || k == 0) return 1;

        int[][] pascalTriangular = new int[n + 1][k + 1];
        pascalTriangular[0][0] = 1;

        for (int row = 1; row <= n; row++) {
            pascalTriangular[row][0] = 1;
            for (int col = 1; col <= k; col++) {
                pascalTriangular[row][col] = pascalTriangular[row - 1][col - 1] + pascalTriangular[row - 1][col];
            }
        }

        return pascalTriangular[n][k];
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
