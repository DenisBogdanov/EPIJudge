package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ABSqrt2 {
    private static final double SQRT_2 = Math.sqrt(2);

    @EpiTest(testDataFile = "a_b_sqrt2.tsv")
    public static List<Double> generateFirstKABSqrt2(int k) {
        List<Double> result = new ArrayList<>(k);
        Queue<Element> minHeap = new PriorityQueue<>(Comparator.comparingDouble(e -> e.value));
        Element initialElement = new Element(0, 0, 0.0);
        minHeap.offer(initialElement);

        Set<Element> seen = new HashSet<>();
        seen.add(initialElement);

        while (k-- > 0) {
            Element polled = minHeap.poll();
            result.add(polled.value);

            int a = polled.a;
            int b = polled.b;

            Element new1 = new Element(a + 1, b, polled.value + 1);
            if (seen.add(new1)) minHeap.offer(new1);

            Element new2 = new Element(a, b + 1, a + (b + 1) * SQRT_2);
            if (seen.add(new2)) minHeap.offer(new2);
        }

        return result;
    }

    private static class Element {
        final int a;
        final int b;
        final double value;

        public Element(int a, int b, double value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return a == element.a && b == element.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ABSqrt2.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
