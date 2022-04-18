package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortedArraysMerge {

  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
  public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
    List<Iterator<Integer>> iterators = new ArrayList<>(sortedArrays.size());

    for (List<Integer> array : sortedArrays) {
      iterators.add(array.iterator());
    }

    PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
        sortedArrays.size(), Comparator.comparingInt(entry -> entry.value));

    for (int i = 0; i < iterators.size(); ++i) {
      if (iterators.get(i).hasNext()) {
        minHeap.add(new ArrayEntry(iterators.get(i).next(), i));
      }
    }

    List<Integer> result = new ArrayList<>();

    while (!minHeap.isEmpty()) {
      ArrayEntry headEntry = minHeap.poll();
      result.add(headEntry.value);
      if (iterators.get(headEntry.arrayld).hasNext()) {
        minHeap.add(new ArrayEntry(iterators.get(headEntry.arrayld).next(),
            headEntry.arrayld));
      }
    }

    return result;
  }

  private static class ArrayEntry {
    public Integer value;
    public Integer arrayld;

    public ArrayEntry(Integer value, Integer arrayld) {
      this.value = value;
      this.arrayld = arrayld;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
