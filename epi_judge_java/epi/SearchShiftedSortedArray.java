package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchShiftedSortedArray {

  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
  public static int searchSmallest(List<Integer> list) {
    int left = 0;
    int right = list.size() - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (list.get(mid) > list.get(right)) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
