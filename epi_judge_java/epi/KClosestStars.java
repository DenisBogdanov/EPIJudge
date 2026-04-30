package epi;

import epi.test_framework.*;

import java.util.*;

public class KClosestStars {

    public static List<Star> findClosestKStars(Iterator<Star> stars, int k) {
        List<Star> ans = new ArrayList<>();
        PriorityQueue<Star> minHeap = new PriorityQueue<>(Comparator.reverseOrder());
        while (stars.hasNext()) {
            Star next = stars.next();
            minHeap.offer(next);
            if (minHeap.size() > k) minHeap.poll();
        }
        while (!minHeap.isEmpty()) ans.add(minHeap.poll());
        return ans;
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
