package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsStaircase {

    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    public static int numberOfWaysToTop(int top, int maximumStep) {
        int[] dp = new int[top + 1];
        dp[0] = 1;

        for (int stair = 1; stair <= top; stair++) {
            for (int prev = stair - 1; prev >= Math.max(0, stair - maximumStep); prev--) {
                dp[stair] += dp[prev];
            }
        }

        return dp[top];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
