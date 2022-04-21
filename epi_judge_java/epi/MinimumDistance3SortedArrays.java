package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

  @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
  public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
    int[] heads = new int[sortedArrays.size()];

    NavigableSet<ArrayData> currentHeads = new TreeSet<>();
    for (int i = 0; i < sortedArrays.size(); i++) {
      currentHeads.add(new ArrayData(i, sortedArrays.get(i).get(0)));
    }

    int result = Integer.MAX_VALUE;

    while (true) {
      result = Math.min(result, currentHeads.last().val - currentHeads.first().val);
      int indexNextMin = currentHeads.first().index;
      heads[indexNextMin] = heads[indexNextMin] + 1;
      if (heads[indexNextMin] >= sortedArrays.get(indexNextMin).size()) {
        return result;
      }

      currentHeads.pollFirst();
      currentHeads.add(new ArrayData(indexNextMin, sortedArrays.get(indexNextMin).get(heads[indexNextMin])));
    }
  }

  public static class ArrayData implements Comparable<ArrayData> {
    public int index;
    public int val;

    public ArrayData(int index, int val) {
      this.val = val;
      this.index = index;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(index, o.index);
      }
      return result;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
