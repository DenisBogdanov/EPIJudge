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
    public static List<Integer> kLargestInBinaryHeap(List<Integer> nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        List<Integer> ans = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ans.add(minHeap.poll());
        }
        return ans;
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
