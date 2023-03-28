package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KLargestInHeap {

    @EpiTest(testDataFile = "k_largest_in_heap.tsv")
    public static List<Integer> kLargestInBinaryHeap(List<Integer> list, int k) {
        List<Integer> result = new ArrayList<>();
        if (k <= 0) return result;

        int n = list.size();
        Queue<HeapEntry> maxHeap = new PriorityQueue<>((e1, e2) -> e2.value - e1.value);
        maxHeap.offer(new HeapEntry(0, list.get(0)));

        for (int i = 0; i < k; i++) {
            int candidateIndex = maxHeap.peek().index;
            result.add(maxHeap.remove().value);

            int leftChildIndex = 2 * candidateIndex + 1;
            if (leftChildIndex < n) {
                maxHeap.offer(new HeapEntry(leftChildIndex, list.get(leftChildIndex)));
            }

            int rightChildIndex = 2 * candidateIndex + 2;
            if (rightChildIndex < n) {
                maxHeap.offer(new HeapEntry(rightChildIndex, list.get(rightChildIndex)));
            }
        }

        return result;
    }

    private static class HeapEntry {
        final int index;
        final int value;

        public HeapEntry(int index, int value) {
            this.index = index;
            this.value = value;
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
