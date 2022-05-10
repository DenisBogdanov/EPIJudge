package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class NumberOfTraversalsStaircase {

  @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")
  public static int numberOfWaysToTop(int top, int maximumStep) {
    return solve(top, maximumStep, new int[top + 1]);
  }

  private static int solve(int top, int maximumStep, int[] numberOfWaysDp) {
    if (top <= 1) {
      return 1;
    }

    if (numberOfWaysDp[top] == 0) {
      for (int i = 1; i <= maximumStep && top - i >= 0; i++) {
        numberOfWaysDp[top] += solve(top - i, maximumStep, numberOfWaysDp);
      }
    }

    return numberOfWaysDp[top];
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
