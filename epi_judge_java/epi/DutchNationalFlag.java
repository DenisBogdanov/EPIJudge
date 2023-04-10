package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DutchNationalFlag {
    public enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> list) {
        Color pivotColor = list.get(pivotIndex);
        int lessIndex = 0;
        int eqIndex = 0;
        int greaterIndex = list.size() - 1;

        while (eqIndex <= greaterIndex) {
            if (list.get(eqIndex) == pivotColor) {
                eqIndex++;
            } else if (list.get(eqIndex).ordinal() < pivotColor.ordinal()) {
                Collections.swap(list, lessIndex, eqIndex);
                lessIndex++;
                eqIndex++;
            } else {
                Collections.swap(list, eqIndex, greaterIndex);
                greaterIndex--;
            }
        }
    }

    @EpiTest(testDataFile = "dutch_national_flag.tsv")
    public static void dutchFlagPartitionWrapper(TimedExecutor executor,
                                                 List<Integer> A, int pivotIdx)
            throws Exception {
        List<Color> colors = new ArrayList<>();
        int[] count = new int[3];

        Color[] C = Color.values();
        for (int i = 0; i < A.size(); i++) {
            count[A.get(i)]++;
            colors.add(C[A.get(i)]);
        }

        Color pivot = colors.get(pivotIdx);
        executor.run(() -> dutchFlagPartition(pivotIdx, colors));

        int i = 0;
        while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        if (i != colors.size()) {
            throw new TestFailure("Not partitioned after " + Integer.toString(i) +
                    "th element");
        } else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
            throw new TestFailure("Some elements are missing from original array");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DutchNationalFlag.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
