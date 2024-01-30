package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestElementInLongArray {

    public static int findKthLargestUnknownLength(Iterator<Integer> stream, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(stream.next());
        }

        while (stream.hasNext()) {
            minHeap.offer(stream.next());
            minHeap.poll();
        }

        return minHeap.poll();
    }

    @EpiTest(testDataFile = "kth_largest_element_in_long_array.tsv")
    public static int findKthLargestUnknownLengthWrapper(List<Integer> stream,
                                                         int k) {
        return findKthLargestUnknownLength(stream.iterator(), k);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KthLargestElementInLongArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
