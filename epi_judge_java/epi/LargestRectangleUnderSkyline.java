package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class LargestRectangleUnderSkyline {

  @EpiTest(testDataFile = "largest_rectangle_under_skyline.tsv")
  public static int calculateLargestRectangle(List<Integer> heights) {
    Deque<Integer> indexStack = new ArrayDeque<>();
    int result = 0;

    for (int i = 0; i <= heights.size(); i++) {
      while (!indexStack.isEmpty() && isNewBuildingOrEnd(heights, i, indexStack.peek())) {
        int height = heights.get(indexStack.pop());
        int width = indexStack.isEmpty() ? i : i - indexStack.peek() - 1;
        result = Math.max(result, width * height);
      }

      indexStack.push(i);
    }

    return result;
  }

  private static boolean isNewBuildingOrEnd(List<Integer> heights, int currIndex, Integer topBuildingIndex) {
    return currIndex >= heights.size() || heights.get(currIndex) < heights.get(topBuildingIndex);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LargestRectangleUnderSkyline.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
