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

        int index = 0;
        int n = disjointIntervals.size();
        while (index < n && disjointIntervals.get(index).right < newInterval.left) {
            result.add(disjointIntervals.get(index));
            index++;
        }

        if (index == n) {
            result.add(newInterval);
            return result;
        }

        int newLeft = Math.min(disjointIntervals.get(index).left, newInterval.left);

        while (index < n && disjointIntervals.get(index).left <= newInterval.right) {
            index++;
        }

        if (index == n) {
            result.add(new Interval(newLeft, Math.max(disjointIntervals.get(n - 1).right, newInterval.right)));
            return result;
        }

        result.add(new Interval(newLeft, Math.max(index == 0 ? Integer.MIN_VALUE : disjointIntervals.get(index - 1).right, newInterval.right)));

        for (int i = index; i < n; i++) {
            result.add(disjointIntervals.get(i));
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
