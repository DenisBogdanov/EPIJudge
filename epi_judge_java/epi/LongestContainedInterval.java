package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class LongestContainedInterval {

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRange(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) return 0;

        nums.sort(null);

        int result = 1;
        int currCount = 1;

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i).equals(nums.get(i - 1))) continue;
            if (nums.get(i) - nums.get(i - 1) == 1) {
                currCount++;
            } else {
                currCount = 1;
            }

            result = Math.max(result, currCount);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
