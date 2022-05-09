package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ThreeSum {

  @EpiTest(testDataFile = "three_sum.tsv")
  public static boolean hasThreeSum(List<Integer> list, int t) {
    Collections.sort(list);

    for (int i = 0; i < list.size(); i++) {
      int target = t - list.get(i);

      int left = i;
      int right = list.size() - 1;

      while (left <= right) {
        if (list.get(left) + list.get(right) == target) {
          return true;
        } else if (list.get(left) + list.get(right) < target) {
          left++;
        } else {
          right--;
        }
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ThreeSum.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
