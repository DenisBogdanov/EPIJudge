package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(Comparator.comparingInt(Entry::value));
        for (int i = 0; i < sortedArrays.size(); i++) {
            if (!sortedArrays.get(i).isEmpty()) {
                minHeap.offer(new Entry(sortedArrays.get(i).get(0), i, 0));
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            var polled = minHeap.poll();
            ans.add(polled.value);
            if (polled.index + 1 < sortedArrays.get(polled.arr).size()) {
                minHeap.offer(new Entry(sortedArrays.get(polled.arr).get(polled.index + 1), polled.arr, polled.index + 1));
            }
        }
        return ans;
    }

    private record Entry(int value, int arr, int index) {}

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
