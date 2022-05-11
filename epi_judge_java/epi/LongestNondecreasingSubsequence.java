package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class LongestNondecreasingSubsequence {

  @EpiTest(testDataFile = "longest_nondecreasing_subsequence.tsv")
  public static int longestNondecreasingSubsequenceLength(List<Integer> list) {
    int n = list.size();
    int[] dp = new int[n];

    for (int i = 0; i < n; i++) {
      int max = 0;
      for (int j = 0; j < i; j++) {
        if (list.get(j) <= list.get(i) && dp[j] > max) {
          max = dp[j];
        }
      }

      dp[i] = 1 + max;
    }

    return Arrays.stream(dp).max().orElseThrow();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestNondecreasingSubsequence.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
