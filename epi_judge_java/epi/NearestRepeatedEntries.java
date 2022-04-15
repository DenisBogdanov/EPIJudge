package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {

  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")
  public static int findNearestRepetition(List<String> paragraph) {
    int minDistance = Integer.MAX_VALUE;
    Map<String, Integer> wordToLastIndexMap = new HashMap<>();

    for (int i = 0; i < paragraph.size(); i++) {
      var word = paragraph.get(i);
      if (wordToLastIndexMap.containsKey(word)) {
        var prevIndex = wordToLastIndexMap.get(word);
        if (i - prevIndex < minDistance) {
          minDistance = i - prevIndex;
        }
      }

      wordToLastIndexMap.put(word, i);
    }

    return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
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
