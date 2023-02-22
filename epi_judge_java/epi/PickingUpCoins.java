package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class PickingUpCoins {

    @EpiTest(testDataFile = "picking_up_coins.tsv")
    public static int pickUpCoins(List<Integer> coins) {
        int sum = 0;
        for (Integer coin : coins) {
            sum += coin;
        }

        int n = coins.size();
        int[][] maxDp = new int[n][n];

        return helper(coins, 0, n - 1, maxDp);
    }

    private static int helper(List<Integer> coins, int left, int right, int[][] maxDp) {
        if (left > right) return 0;

        if (maxDp[left][right] != 0) return maxDp[left][right];

        int leftResult = coins.get(left) + Math.min(
                helper(coins, left + 2, right, maxDp),
                helper(coins, left + 1, right - 1, maxDp));

        int rightResult = coins.get(right) + Math.min(
                helper(coins, left, right - 2, maxDp),
                helper(coins, left + 1, right - 1, maxDp));

        return maxDp[left][right] = Math.max(leftResult, rightResult);
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
