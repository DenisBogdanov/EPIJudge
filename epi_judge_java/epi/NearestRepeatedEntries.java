package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {

    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
    public static int findNearestRepetition(List<String> paragraph) {
        Map<String, Integer> wordsToLastIndexMap = new HashMap<>();
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < paragraph.size(); i++) {
            if (wordsToLastIndexMap.containsKey(paragraph.get(i))) {
                result = Math.min(result, i - wordsToLastIndexMap.get(paragraph.get(i)));
            }

            wordsToLastIndexMap.put(paragraph.get(i), i);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
