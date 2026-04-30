package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {
    public static List<Double> onlineMedian(Iterator<Integer> sequence) {
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();
        List<Double> ans = new ArrayList<>();
        while (sequence.hasNext()) {
            int next = sequence.next();
            left.offer(next);
            right.offer(left.poll());
            while (right.size() > left.size()) {
                left.offer(right.poll());
            }
            if (left.size() == right.size()) {
                ans.add((left.peek() + right.peek()) / 2.0);
            } else {
                ans.add(left.peek().doubleValue());
            }
        }
        return ans;
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
