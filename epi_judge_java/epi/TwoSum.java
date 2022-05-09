package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class TwoSum {

  @EpiTest(testDataFile = "two_sum.tsv")
  public static boolean hasTwoSum(List<Integer> list, int t) {
    Collections.sort(list);
    int left = 0;
    int right = list.size() - 1;

    while (left <= right) {
      if (list.get(left) + list.get(right) == t) {
        return true;
      } else if (list.get(left) + list.get(right) < t) {
        left++;
      } else {
        right--;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSum.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
