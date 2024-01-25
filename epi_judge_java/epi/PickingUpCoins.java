package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PickingUpCoins {
    private static Map<Pair, Integer> cache;
    private static int counter = 0;

    @EpiTest(testDataFile = "picking_up_coins.tsv")
    public static int pickUpCoins(List<Integer> coins) {
        counter++;
        cache = new HashMap<>();

        int totalSum = 0;
        for (Integer coin : coins) {
            totalSum += coin;
        }

        int maxDiff = helper(coins, 0, coins.size() - 1);
        return (totalSum - maxDiff) / 2 + maxDiff;
    }

    private static int helper(List<Integer> coins, int left, int right) {
        if (left == right) return coins.get(left);
        Pair pair = new Pair(left, right);
        Integer result = cache.get(pair);
        if (result != null) return result;

        result = Math.max(
                coins.get(left) - helper(coins, left + 1, right),
                coins.get(right) - helper(coins, left, right - 1));

        cache.put(pair, result);

        return result;
    }

    private static class Pair {
        final int left;
        final int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return left == pair.left && right == pair.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right);
        }
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
