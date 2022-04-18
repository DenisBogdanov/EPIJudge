package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SmallestSubarrayCoveringAllValues {

  public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph,
                                                                List<String> keywords) {

    Map<String, TreeSet<Integer>> keywordToPositionsMap = keywords.stream()
        .collect(Collectors.toMap(Function.identity(), keyword -> new TreeSet<>()
            , (o, n) -> new TreeSet<>(), LinkedHashMap::new));

    for (int i = 0; i < paragraph.size(); i++) {
      if (keywordToPositionsMap.containsKey(paragraph.get(i))) {
        keywordToPositionsMap.get(paragraph.get(i)).add(i);
      }
    }

    Subarray result = new Subarray(-1, -1);

    var firstWord = keywords.get(0);
    for (Integer firstWordPosition : keywordToPositionsMap.get(firstWord)) {
      int currentStart = firstWordPosition;
      int prevLetterPosition = firstWordPosition;
      boolean success = true;

      for (Map.Entry<String, TreeSet<Integer>> wordToPositionEntry : keywordToPositionsMap.entrySet()) {
        if (wordToPositionEntry.getKey().equals(firstWord)) continue;

        var nextLetterPosition = wordToPositionEntry.getValue().ceiling(prevLetterPosition);
        if (nextLetterPosition == null) {
          success = false;
          break;
        }

        prevLetterPosition = nextLetterPosition;
      }

      if (success
          && ((result.start == -1 && result.end == -1)
          || (prevLetterPosition - currentStart < result.end - result.start))) {

        result.start = currentStart;
        result.end = prevLetterPosition;
      }
    }

    return result;
  }

  public static class Subarray {
    // Represent subarray by starting and ending indices, inclusive.
    public Integer start;
    public Integer end;

    public Subarray(Integer start, Integer end) {
      this.start = start;
      this.end = end;
    }
  }

  @EpiTest(testDataFile = "smallest_subarray_covering_all_values.tsv")
  public static int findSmallestSequentiallyCoveringSubsetWrapper(
      TimedExecutor executor, List<String> paragraph, List<String> keywords)
      throws Exception {
    Subarray result = executor.run(
        () -> findSmallestSequentiallyCoveringSubset(paragraph, keywords));

    int kwIdx = 0;
    if (result.start < 0) {
      throw new TestFailure("Subarray start index is negative");
    }
    int paraIdx = result.start;

    while (kwIdx < keywords.size()) {
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Not all keywords are in the generated subarray");
      }
      if (paraIdx >= paragraph.size()) {
        throw new TestFailure("Subarray end index exceeds array size");
      }
      if (paragraph.get(paraIdx).equals(keywords.get(kwIdx))) {
        kwIdx++;
      }
      paraIdx++;
    }
    return result.end - result.start + 1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestSubarrayCoveringAllValues.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
