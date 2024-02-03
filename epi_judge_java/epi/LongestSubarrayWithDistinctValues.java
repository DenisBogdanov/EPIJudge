package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubarrayWithDistinctValues {

    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    public static int longestSubarrayWithDistinctEntries(List<Integer> nums) {
        Set<Integer> seen = new HashSet<>();
        int result = 0;

        int start = 0;
        for (int end = 0; end < nums.size(); end++) {
            if (seen.add(nums.get(end))) {
                result = Math.max(result, end - start + 1);
            } else {
                while (seen.contains(nums.get(end))) {
                    seen.remove(nums.get(start));
                    start++;
                }

                seen.add(nums.get(end));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
