package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxSteps) {
    int maxPossiblePosition = 0;
    int currentPosition = 0;

    while (currentPosition <= maxPossiblePosition && currentPosition < maxSteps.size()) {
      maxPossiblePosition = Math.max(
          maxPossiblePosition,
          currentPosition + maxSteps.get(currentPosition));
      currentPosition++;
    }

    return maxPossiblePosition >= maxSteps.size() - 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());

//    canReachEnd(List.of(3, 3, 1, 0, 2, 0, 1));
  }
}
