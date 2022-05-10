package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MinimumWeightPathInATriangle {

  @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
  public static int minimumPathTotal(List<List<Integer>> triangle) {
    int n = triangle.size();

    int[] dp = new int[n + 1];
    for (int row = n - 1; row >= 0; row--) {
      for (int col = 0; col < triangle.get(row).size(); col++) {
        dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
      }
    }

    return dp[0];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
