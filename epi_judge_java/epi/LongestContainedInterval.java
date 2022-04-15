package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class LongestContainedInterval {

  @EpiTest(testDataFile = "longest_contained_interval.tsv")
  public static int longestContainedRange(List<Integer> list) {
//    return initialSolutionWithSortingIsActuallyFaster(list);


    Set<Integer> set = new HashSet<>(list);

    int result = 0;

    while (!set.isEmpty()) {
      int left = 0;
      int right = 0;
      var num = set.iterator().next();
      set.remove(num);

      while (set.contains(num - 1 - left)) {
        set.remove(num - 1 - left++);
      }

      while (set.contains(num + 1 + right)) {
        set.remove(num + 1 + right++);
      }

      if (result < left + 1 + right) {
        result = left + 1 + right;
      }
    }

    return result;
  }

  private static int initialSolutionWithSortingIsActuallyFaster(List<Integer> list) {
    Collections.sort(list);
    int result = 0;
    int currentLength = 1;

    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i).equals(list.get(i + 1))) continue;

      if (list.get(i) + 1 == list.get(i + 1)) {
        currentLength++;
      } else {
        if (currentLength > result) {
          result = currentLength;
        }

        currentLength = 1;
      }
    }

    return Math.max(result, currentLength);
  }

  public static void main(String[] args) {
    longestContainedRange(new ArrayList<>(List.of(17, 26, 19, 32, 28, 6, 19, 15, 30, 6, 11, 33, 32, 34, 9, 1, 9, 11, 2, 30, 33, 6, 18, 19, 20, 27, 11, 7, 35, 0, 31, 12, 33, 30, 24)));

    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestContainedInterval.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
