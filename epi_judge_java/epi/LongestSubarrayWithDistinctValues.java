package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubarrayWithDistinctValues {

  @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")
  public static int longestSubarrayWithDistinctEntries(List<Integer> list) {
    if (list.isEmpty()) return 0;

    Map<Integer, Integer> visitedToLastPositionMap = new HashMap<>();
    int result = 1;

    int currentStart = 0;

    for (int i = 0; i < list.size(); i++) {
      if (visitedToLastPositionMap.containsKey(list.get(i))) {
        if (i - currentStart > result) {
          result = i - currentStart;
        }

        currentStart = Math.max(currentStart, visitedToLastPositionMap.get(list.get(i)) + 1);
      }

      visitedToLastPositionMap.put(list.get(i), i);
    }

    if (list.size() - currentStart > result) {
      result = list.size() - currentStart;
    }

    return result;
  }

  public static void main(String[] args) {
//    System.out.println(longestSubarrayWithDistinctEntries(List.of(1, 2, 1, 3, 1, 2, 1)));

    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
