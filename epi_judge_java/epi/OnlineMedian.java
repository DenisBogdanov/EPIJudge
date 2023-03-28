package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class OnlineMedian {

    public static List<Double> onlineMedian(Iterator<Integer> sequence) {
        Queue<Integer> less = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> greater = new PriorityQueue<>();

        List<Double> result = new ArrayList<>();
        while (sequence.hasNext()) {
            less.offer(sequence.next());
            if (!greater.isEmpty()) {
                less.offer(greater.poll());
            }

            while (less.size() > greater.size()) {
                greater.offer(less.poll());
            }

            if (less.size() == greater.size()) {
                result.add(((double) less.peek() + greater.peek()) / 2);
            } else {
                result.add((double) greater.peek());
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
