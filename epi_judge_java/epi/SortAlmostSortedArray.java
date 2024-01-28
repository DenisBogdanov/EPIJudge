package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SortAlmostSortedArray {

    public static List<Integer> sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new PriorityQueue<>();

        while (sequence.hasNext()) {
            q.offer(sequence.next());

            if (q.size() > k) {
                result.add(q.poll());
            }
        }

        while (!q.isEmpty()) {
            result.add(q.poll());
        }

        return result;
    }

    @EpiTest(testDataFile = "sort_almost_sorted_array.tsv")
    public static List<Integer>
    sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
        return sortApproximatelySortedData(sequence.iterator(), k);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortAlmostSortedArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
