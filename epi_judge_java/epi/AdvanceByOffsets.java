package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class AdvanceByOffsets {

    @EpiTest(testDataFile = "advance_by_offsets.tsv")
    public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
        var maxSoFar = 0;
        for (int i = 0; i < maxAdvanceSteps.size(); i++) {
            if (maxSoFar < i) return false;
            maxSoFar = Math.max(maxSoFar, i + maxAdvanceSteps.get(i));
            if (maxSoFar >= maxAdvanceSteps.size()) break;
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
