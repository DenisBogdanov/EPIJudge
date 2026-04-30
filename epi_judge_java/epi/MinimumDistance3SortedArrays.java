package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

    @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")
    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        if (sortedArrays.isEmpty()) return 0;
        List<Integer> minLenList = sortedArrays.get(0);
        for (int i = 1; i < sortedArrays.size(); i++) {
            if (sortedArrays.get(i).size() < minLenList.size()) minLenList = sortedArrays.get(i);
        }

        TreeSet<Entry> treeSet = new TreeSet<>(Comparator.comparing(Entry::value).thenComparing(Entry::arr));
        for (int i = 0; i < sortedArrays.size(); i++) {
            treeSet.add(new Entry(sortedArrays.get(i).get(0), i, 0));
        }

        int ans = treeSet.last().value - treeSet.first().value;
        while (true) {
            var firstEntry = treeSet.pollFirst();
            if (sortedArrays.get(firstEntry.arr).size() == firstEntry.idx + 1) break;
            treeSet.add(new Entry(sortedArrays.get(firstEntry.arr).get(firstEntry.idx + 1),
                    firstEntry.arr, firstEntry.idx + 1));
            ans = Math.min(ans, treeSet.last().value - treeSet.first().value);
        }

        return ans;
    }

    private record Entry(int value, int arr, int idx) {}

    public static class ArrayData implements Comparable<ArrayData> {
        public int val;
        public int idx;

        public ArrayData(int idx, int val) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ArrayData o) {
            int result = Integer.compare(val, o.val);
            if (result == 0) {
                result = Integer.compare(idx, o.idx);
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
