package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class AdvanceByOffsets {

    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        int n = maxAdvanceSteps.size();

        int currMax = maxAdvanceSteps.get(0);
        for (int i = 1; i < n; i++) {
            if (i > currMax) return false;
            currMax = Math.max(currMax, i + maxAdvanceSteps.get(i));
            if (currMax >= n) return true;
        }

        return true;
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
