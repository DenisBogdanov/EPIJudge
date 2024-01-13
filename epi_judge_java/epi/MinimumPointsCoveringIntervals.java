package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;

public class MinimumPointsCoveringIntervals {
    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
    public static Integer findMinimumVisits(List<Interval> intervals) {
        intervals.sort(Comparator.comparingInt(i -> i.right));

        int result = 0;
        int prevVisitTime = -1;

        for (Interval interval : intervals) {
            if (prevVisitTime < interval.left) {
                prevVisitTime = interval.right;
                result++;
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
