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
        List<Integer> result = new ArrayList<>();

        Queue<Pair> q = new PriorityQueue<>(Comparator.comparingInt(p -> sortedArrays.get(p.arrIndex).get(p.index)));
        for (int i = 0; i < sortedArrays.size(); i++) {
            q.offer(new Pair(i, 0));
        }

        while (!q.isEmpty()) {
            Pair polled = q.poll();
            List<Integer> arr = sortedArrays.get(polled.arrIndex);
            result.add(arr.get(polled.index));
            if (polled.index + 1 < arr.size()) {
                q.offer(new Pair(polled.arrIndex, polled.index + 1));
            }
        }

        return result;
    }

    private static class Pair {
        final int arrIndex;
        int index;

        public Pair(int arrIndex, int index) {
            this.arrIndex = arrIndex;
            this.index = index;
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
