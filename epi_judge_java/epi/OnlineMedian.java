package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class OnlineMedian {

    public static List<Double> onlineMedian(Iterator<Integer> sequence) {
        Queue<Integer> q1 = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> q2 = new PriorityQueue<>();

        List<Double> result = new ArrayList<>();

        while (sequence.hasNext()) {
            int next = sequence.next();

            q1.offer(next);
            while (q1.size() > q2.size() + 1) {
                q2.offer(q1.poll());
            }

            if (!q2.isEmpty() && q1.peek() > q2.peek()) {
                q1.offer(q2.poll());
                q2.offer(q1.poll());
            }

            if (q1.size() == q2.size()) {
                result.add((q1.peek() + q2.peek()) / 2.0);
            } else {
                result.add(Double.valueOf(q1.peek()));
            }
        }

        return result;
    }

    @EpiTest(testDataFile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
        return onlineMedian(sequence.iterator());
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
