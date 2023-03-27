package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SortedArraysMerge {

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        Queue<ListEntry> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < sortedArrays.size(); i++) {
            minHeap.offer(new ListEntry(sortedArrays.get(i).get(0), i, 0));
        }

        while (!minHeap.isEmpty()) {
            ListEntry polled = minHeap.poll();
            result.add(polled.value);

            List<Integer> arr = sortedArrays.get(polled.arrIndex);
            if (polled.elementIndex + 1 < arr.size()) {
                minHeap.offer(new ListEntry(arr.get(polled.elementIndex + 1), polled.arrIndex, polled.elementIndex + 1));
            }
        }

        return result;
    }

    private static class ListEntry {
        final int value;
        final int arrIndex;
        final int elementIndex;

        public ListEntry(int value, int arrIndex, int elementIndex) {
            this.value = value;
            this.arrIndex = arrIndex;
            this.elementIndex = elementIndex;
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
