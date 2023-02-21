package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;

public class MinimumPointsCoveringIntervals {

    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
    public static Integer findMinimumVisits(List<Interval> intervals) {
        if (intervals.isEmpty()) return 0;
        intervals.sort(Comparator.comparingInt(interval -> interval.right));

        int result = 1;
        int prevVisit = intervals.get(0).right;

        for (Interval interval : intervals) {
            if (interval.left > prevVisit) {
                result++;
                prevVisit = interval.right;
            }
        }

        return result;
    }

    @EpiUserType(ctorParams = {int.class, int.class})
    public static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
