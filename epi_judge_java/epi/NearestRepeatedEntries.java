package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {

    @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
    public static int findNearestRepetition(List<String> paragraph) {
        Map<String, Integer> wordToPrevIndexMap = new HashMap<>();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < paragraph.size(); i++) {
            String currWord = paragraph.get(i);
            Integer prevIndex = wordToPrevIndexMap.get(currWord);
            if (prevIndex != null) {
                result = Math.min(result, i - prevIndex);
            }

            wordToPrevIndexMap.put(currWord, i);
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
