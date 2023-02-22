package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsStaircase {

    @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
    public static int numberOfWaysToTop(int top, int maximumStep) {
        int[] waysToSizeDp = new int[top + 1];
        waysToSizeDp[0] = 1;

        int currSum = 1;
        for (int i = 1; i <= Math.min(maximumStep, top); i++) {
            waysToSizeDp[i] = currSum;
            currSum *= 2;
        }

        currSum /= 2;

        for (int i = maximumStep + 1; i <= top; i++) {
            currSum *= 2;
            currSum -= waysToSizeDp[i - maximumStep - 1];
            waysToSizeDp[i] = currSum;
        }

        return waysToSizeDp[top];
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
