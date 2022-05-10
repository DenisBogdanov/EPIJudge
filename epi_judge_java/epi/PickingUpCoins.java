package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class PickingUpCoins {

  @EpiTest(testDataFile = "picking_up_coins.tsv")
  public static int pickUpCoins(List<Integer> coins) {
    return solve(coins, 0, coins.size() - 1, new int[coins.size()][coins.size()]);
  }

  private static int solve(List<Integer> coins, int left, int right, int[][] dp) {
    if (left > right) {
      return 0;
    }

    if (dp[left][right] == 0) {
      int maxRevenueLeft = coins.get(left) +
          Math.min(solve(coins, left + 2, right, dp), solve(coins, left + 1, right - 1, dp));

      int maxRevenueRight = coins.get(right) +
          Math.min(solve(coins, left + 1, right - 1, dp), solve(coins, left, right - 2, dp));

      dp[left][right] = Math.max(maxRevenueLeft, maxRevenueRight);
    }

    return dp[left][right];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PickingUpCoins.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
