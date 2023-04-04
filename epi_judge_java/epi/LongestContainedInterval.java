package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class LongestContainedInterval {

    @EpiTest(testDataFile = "longest_contained_interval.tsv")
    public static int longestContainedRange(List<Integer> list) {
        Collections.sort(list);
        int result = 1;
        int currRange = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).equals(list.get(i - 1))) continue;

            if (list.get(i) == list.get(i - 1) + 1) {
                currRange++;
                result = Math.max(result, currRange);
            } else {
                currRange = 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
