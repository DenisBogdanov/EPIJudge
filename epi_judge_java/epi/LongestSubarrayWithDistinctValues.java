package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestSubarrayWithDistinctValues {

    @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
    public static int longestSubarrayWithDistinctEntries(List<Integer> nums) {
        if (nums.isEmpty()) return 0;
        Set<Integer> seen = new HashSet<>();
        int ans = 1;
        int start = 0;
        for (int num : nums) {
            if (seen.contains(num)) {
                while (!nums.get(start).equals(num)) {
                    seen.remove(nums.get(start));
                    start++;
                }
                start++;
            }
            seen.add(num);
            ans = Math.max(ans, seen.size());
        }
        return ans;
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
