package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class MinimumPointsCoveringIntervals {

  @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")
  public static Integer findMinimumVisits(List<Interval> intervals) {
    if (intervals == null || intervals.isEmpty()) return 0;

    // already sorted
    // intervals.sort(Comparator.comparingInt(i -> i.left));

    int result = 1;
    int currentVisitTime = intervals.get(0).right;

    for (int i = 1; i < intervals.size(); i++) {
      if (currentVisitTime < intervals.get(i).left) {
        result++;
        currentVisitTime = intervals.get(i).right;
      } else if (currentVisitTime > intervals.get(i).right) {
        currentVisitTime = intervals.get(i).right;
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
