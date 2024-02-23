package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class LongestIncreasingSubarray {

    // Represent subarray by starting and ending indices, inclusive.
    private static class Subarray {
        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Subarray findLongestIncreasingSubarray(List<Integer> nums) {
        int start = 0;
        int result = 1;
        int bestStart = 0;

        int n = nums.size();
        for (int end = 1; end < n; end++) {
            if (nums.get(end) <= nums.get(end - 1)) {
                if (result < end - start) {
                    result = end - start;
                    bestStart = start;
                }
                start = end;
            }
        }

        if (result < n - start) {
            result = n - start;
            bestStart = start;
        }

        return new Subarray(bestStart, bestStart + result - 1);
    }

    @EpiTest(testDataFile = "longest_increasing_subarray.tsv")
    public static int findLongestIncreasingSubarrayWrapper(List<Integer> A) {
        Subarray result = findLongestIncreasingSubarray(A);
        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestIncreasingSubarray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
