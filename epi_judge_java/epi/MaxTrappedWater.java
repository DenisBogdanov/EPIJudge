package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MaxTrappedWater {

  @EpiTest(testDataFile = "max_trapped_water.tsv")
  public static int getMaxTrappedWater(List<Integer> heights) {
    int result = 0;
    int left = 0;
    int right = heights.size() - 1;

    while (left < right) {
      int currArea = Math.min(heights.get(left), heights.get(right)) * (right - left);
      if (currArea > result) {
        result = currArea;
      }

      if (heights.get(left) < heights.get(right)) {
        left++;
      } else if (heights.get(left) > heights.get(right)) {
        right--;
      } else {
        left++;
        right--;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxTrappedWater.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
