package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class Knapsack {

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
        int[] dp = new int[capacity + 1];
        for (Item item : items) {
            for (int i = capacity; i >= item.weight; i--) {
                dp[i] = Math.max(dp[i], item.value + dp[i - item.weight]);
            }
        }
        int ans = 0;
        for (int d : dp) {
            ans = Math.max(ans, d);
        }
        return ans;
    }

    @EpiUserType(ctorParams = {Integer.class, Integer.class})
    public static class Item {
        public Integer weight;
        public Integer value;

        public Item(Integer weight, Integer value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Knapsack.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
