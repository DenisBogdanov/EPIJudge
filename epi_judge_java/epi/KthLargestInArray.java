package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KthLargestInArray {
  // The numbering starts from one, i.e., if A = [3,1,-1,2] then
  // findKthLargest(1, A) returns 3, findKthLargest(2, A) returns 2,
  // findKthLargest(3, A) returns 1, and findKthLargest(4, A) returns -1.
  @EpiTest(testDataFile = "kth_largest_in_array.tsv")
  public static int findKthLargest(int k, List<Integer> list) {
    int left = 0;
    int right = list.size() - 1;
    Random rand = new Random(0);

    while (left <= right) {
      int pivotIndex = rand.nextInt(right - left + 1) + left;
      int newPivotIndex = partitionAroundPivot(left, right, pivotIndex, list);

      if (newPivotIndex == k - 1) {
        return list.get(newPivotIndex);
      } else if (newPivotIndex > k - 1) {
        right = newPivotIndex - 1;
      } else {
        left = newPivotIndex + 1;
      }
    }

    throw new IllegalArgumentException();
  }

  private static int partitionAroundPivot(int left, int right, int pivotIndex, List<Integer> list) {
    int pivotValue = list.get(pivotIndex);
    int newPivotIndex = left;

    Collections.swap(list, pivotIndex, right);
    for (int i = left; i < right; i++) {
      if (list.get(i) > pivotValue) {
        Collections.swap(list, i, newPivotIndex++);
      }
    }

    Collections.swap(list, right, newPivotIndex);
    return newPivotIndex;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestInArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
