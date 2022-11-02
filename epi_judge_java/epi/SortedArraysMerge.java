package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class SortedArraysMerge {

    @EpiTest(testDataFile = "sorted_arrays_merge.tsv")
    public static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Integer> result = new ArrayList<>();

        int n = sortedArrays.size();
        Queue<Num> minHeap = new PriorityQueue<>(n, Comparator.comparingInt(num -> num.value));
        int[] indexes = new int[n];
        Arrays.fill(indexes, 1);

        for (int i = 0; i < n; i++) {
            if (sortedArrays.get(i).isEmpty()) continue;
            minHeap.offer(new Num(i, sortedArrays.get(i).get(0)));
        }

        while (!minHeap.isEmpty()) {
            Num polled = minHeap.poll();
            result.add(polled.value);

            int arrId = polled.arrId;
            List<Integer> arr = sortedArrays.get(arrId);
            if (indexes[arrId] < arr.size()) {
                minHeap.offer(new Num(arrId, arr.get(indexes[arrId])));
                indexes[arrId]++;
            }
        }

        return result;
    }

    private static class Num {
        final int arrId;
        final int value;

        public Num(int arrId, int value) {
            this.arrId = arrId;
            this.value = value;
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
