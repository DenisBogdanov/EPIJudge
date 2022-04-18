package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortIncreasingDecreasingArray {

  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> list) {
    List<List<Integer>> sortedSubLists = new ArrayList<>();

    boolean increasing = true;
    int startIndex = 0;

    for (int i = 1; i <= list.size(); i++) {
      if (i == list.size()
          || (list.get(i - 1) < list.get(i) && !increasing)
          || (list.get(i - 1) >= list.get(i) && increasing)) {

        List<Integer> subList = list.subList(startIndex, i);
        if (!increasing) {
          Collections.reverse(subList);
        }

        sortedSubLists.add(subList);
        startIndex = i;
        increasing = !increasing;
      }
    }

    return SortedArraysMerge.mergeSortedArrays(sortedSubLists);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
