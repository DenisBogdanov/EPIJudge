package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ABSqrt2 implements Comparable<ABSqrt2> {
  private static final double SQRT_2 = Math.sqrt(2);

  private final int a;
  private final int b;
  private final double val;

  public ABSqrt2(int a, int b) {
    this.a = a;
    this.b = b;
    this.val = a + b * SQRT_2;
  }

  @EpiTest(testDataFile = "a_b_sqrt2.tsv")
  public static List<Double> generateFirstKABSqrt2(int k) {
    SortedSet<ABSqrt2> candidates = new TreeSet<>();
    candidates.add(new ABSqrt2(0, 0));

    List<Double> result = new ArrayList<>(k);
    while (result.size() < k) {
      ABSqrt2 nextSmallest = candidates.first();
      result.add(nextSmallest.val);
      candidates.add(new ABSqrt2(nextSmallest.a + 1, nextSmallest.b));
      candidates.add(new ABSqrt2(nextSmallest.a, nextSmallest.b + 1));
      candidates.remove(nextSmallest);
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ABSqrt2.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }

  @Override
  public int compareTo(ABSqrt2 o) {
    return Double.compare(val, o.val);
  }
}
