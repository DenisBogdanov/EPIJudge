package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class LongestContainedInterval {

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRange(List<Integer> nums) {
        nums.sort(null);
        int ans = 1;
        int start = nums.get(0);
        int curr = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) == curr) continue;
            if (nums.get(i) == curr + 1) {
                curr++;
                ans = Math.max(ans, curr - start + 1);
            } else {
                start = nums.get(i);
                curr = nums.get(i);
            }
        }
        return ans;
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
