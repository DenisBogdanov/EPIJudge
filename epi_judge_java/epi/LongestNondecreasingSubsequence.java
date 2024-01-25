package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class LongestNondecreasingSubsequence {

    @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
    public static int longestNondecreasingSubsequenceLength(List<Integer> nums) {
        int[] dp = new int[nums.size()];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.size(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums.get(i) >= nums.get(j)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().orElseThrow();
    }

    public static void main(String[] args) {
        longestNondecreasingSubsequenceLength(List.of(5, 16, 5, 3, 9, 16, 20, 0, 6, 10, 12, 11, 6, 16, 10, 19, 20, 16, 13, 6));

        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
