package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.EpiTestExpectedType;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestStars {

    public static List<Star> findClosestKStars(Iterator<Star> stars, int k) {
        Queue<Star> q = new PriorityQueue<>(Comparator.comparingDouble(Star::distance).reversed());
        while (stars.hasNext()) {
            q.offer(stars.next());
            if (q.size() > k) {
                q.poll();
            }
        }

        List<Star> result = new ArrayList<>();
        while (!q.isEmpty()) {
            result.add(q.poll());
        }

        Collections.reverse(result);

        return result;
    }

    @EpiUserType(ctorParams = {double.class, double.class, double.class})
    public static class Star implements Comparable<Star> {
        private double x, y, z;

        public Star(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double distance() {
            return Math.sqrt(x * x + y * y + z * z);
        }

        @Override
        public int compareTo(Star that) {
            return Double.compare(this.distance(), that.distance());
        }

        @Override
        public String toString() {
            return String.valueOf(distance());
        }
    }

    @EpiTest(testDataFile = "k_closest_stars.tsv")
    public static List<Star> findClosestKStarsWrapper(List<Star> stars, int k) {
        return findClosestKStars(stars.iterator(), k);
    }

    @EpiTestExpectedType
    public static List<Double> expectedType;

    @EpiTestComparator
    public static boolean comp(List<Double> expected, List<Star> result) {
        if (expected.size() != result.size()) {
            return false;
        }
        Collections.sort(result);
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).distance() != expected.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "KClosestStars.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
