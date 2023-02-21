package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class Knapsack {

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
        int[][] cache = new int[items.size()][capacity + 1];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }

        return helper(items, 0, capacity, cache);
    }

    private static int helper(List<Item> items, int index, int capacity, int[][] cache) {
        if (index == items.size()) return 0;

        if (cache[index][capacity] != -1) return cache[index][capacity];

        int result = 0;
        if (items.get(index).weight <= capacity) {
            result = items.get(index).value + helper(items, index + 1, capacity - items.get(index).weight, cache);
        }

        return cache[index][capacity] = Math.max(
                result,
                helper(items, index + 1, capacity, cache));
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
