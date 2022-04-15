package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchFirstKey {

  @EpiTest(testDataFile = "search_first_key.tsv")
  public static int searchFirstOfK(List<Integer> list, int key) {
    int left = 0;
    int right = list.size() - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (list.get(mid) == key) {
        if (mid == 0 || list.get(mid - 1) != key) {
          return mid;
        }

        right = mid - 1;
      } else if (list.get(mid) > key) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
