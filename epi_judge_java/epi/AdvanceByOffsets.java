package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class AdvanceByOffsets {

    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int maxIndex = 0;
        for (int i = 0; i <= Math.min(maxIndex, maxAdvanceSteps.size() - 1); i++) {
            maxIndex = Math.max(maxIndex, i + maxAdvanceSteps.get(i));
        }

        return maxIndex >= maxAdvanceSteps.size() - 1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
