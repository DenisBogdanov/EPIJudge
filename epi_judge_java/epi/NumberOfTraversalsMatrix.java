package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsMatrix {

  @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")
  public static int numberOfWays(int n, int m) {
    int[][] grid = new int[n][m];

    for (int i = 0; i < m; i++) {
      grid[0][i] = 1;
    }

    for (int i = 0; i < n; i++) {
      grid[i][0] = 1;
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
      }
    }


    return grid[n - 1][m - 1];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
