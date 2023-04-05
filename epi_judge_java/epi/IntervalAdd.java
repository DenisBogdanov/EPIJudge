package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntervalAdd {

    @EpiTest(testDataFile = "interval_add.tsv")
    public static List<Interval> addInterval(List<Interval> disjointIntervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        int currInterval = 0;
        int n = disjointIntervals.size();

        while (currInterval < n
                && disjointIntervals.get(currInterval).right < newInterval.left) {

            result.add(disjointIntervals.get(currInterval));
            currInterval++;
        }

        if (currInterval == n) {
            result.add(newInterval);
            return result;
        }

        int newIntervalLeft = Math.min(disjointIntervals.get(currInterval).left, newInterval.left);
        while (currInterval < n && disjointIntervals.get(currInterval).left <= newInterval.right) {
            currInterval++;
        }

        int newIntervalRight = newInterval.right;
        if (currInterval > 0) {
            newIntervalRight = Math.max(disjointIntervals.get(currInterval - 1).right, newIntervalRight);
        }

        result.add(new Interval(newIntervalLeft, newIntervalRight));

        while (currInterval < n) {
            result.add(disjointIntervals.get(currInterval));
            currInterval++;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Interval interval = (Interval) o;

            if (left != interval.left) {
                return false;
            }
            return right == interval.right;
        }

        @Override
        public String toString() {
            return "[" + left + ", " + right + "]";
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntervalAdd.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
