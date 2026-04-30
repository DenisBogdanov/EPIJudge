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
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < paragraph.size(); i++) {
            String word = paragraph.get(i);
            Integer prevIndex = wordToPrevIndexMap.get(word);
            if (prevIndex != null) ans = Math.min(ans, i - prevIndex);
            wordToPrevIndexMap.put(word, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
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
