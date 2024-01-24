package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Knapsack {

    @EpiTest(testDataFile = "knapsack.tsv")
    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {
        items.sort(Comparator.comparingInt(i -> i.weight));
        int[] maxValuesByWeight = new int[capacity + 1];
        Arrays.fill(maxValuesByWeight, -1);
        maxValuesByWeight[0] = 0;

        for (Item item : items) {
            for (int i = capacity; i >= item.weight; i--) {
                if (maxValuesByWeight[i - item.weight] == -1) continue;
                maxValuesByWeight[i] = Math.max(maxValuesByWeight[i], maxValuesByWeight[i - item.weight] + item.value);
            }
        }

        return Arrays.stream(maxValuesByWeight).max().orElseThrow();
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
