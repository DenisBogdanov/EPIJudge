package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KLargestInHeap {

  @EpiTest(testDataFile = "k_largest_in_heap.tsv")
  public static List<Integer> kLargestInBinaryHeap(List<Integer> list, int k) {
    if (k <= 0) return List.of();

    PriorityQueue<HeapEntry> maxHeap = new PriorityQueue<>();
    maxHeap.add(new HeapEntry(0, list.get(0)));

    List<Integer> result = new ArrayList<>(k);

    for (int i = 0; i < k; i++) {
      int candidateIndex = maxHeap.peek().index;
      result.add(maxHeap.remove().value);

      int leftChildIndex = 2 * candidateIndex + 1;
      if (leftChildIndex < list.size()) {
        maxHeap.add(new HeapEntry(leftChildIndex, list.get(leftChildIndex)));
      }

      int rightChildIndex = 2 * candidateIndex + 2;
      if (rightChildIndex < list.size()) {
        maxHeap.add(new HeapEntry(rightChildIndex, list.get(rightChildIndex)));
      }
    }

    return result;
  }

  private static class HeapEntry implements Comparable<HeapEntry> {
    final int index;
    final int value;

    public HeapEntry(int index, int value) {
      this.index = index;
      this.value = value;
    }

    @Override
    public int compareTo(HeapEntry other) {
      return other.value - this.value;
    }
  }

  @EpiTestComparator
  public static boolean comp(List<Integer> expected, List<Integer> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
