package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> list1, int m,
                                          List<Integer> list2, int n) {

    int i1 = m - 1;
    int i2 = n - 1;
    int indexToWrite = m + n - 1;

    while (i1 >= 0 && i2 >= 0) {
      if (list1.get(i1) > list2.get(i2)) {
        list1.set(indexToWrite--, list1.get(i1--));
      } else {
        list1.set(indexToWrite--, list2.get(i2--));
      }
    }

    while (i2 >= 0) {
      list1.set(indexToWrite--, list2.get(i2--));
    }
  }

  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
