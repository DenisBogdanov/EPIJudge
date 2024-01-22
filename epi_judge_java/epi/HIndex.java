package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class HIndex {

    @EpiTest(testDataFile = "h_index.tsv")
    public static int hIndex(List<Integer> citations) {
        citations.sort(Collections.reverseOrder());

        int result = 0;
        while (result < citations.size() && citations.get(result) > result) {
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "HIndex.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
