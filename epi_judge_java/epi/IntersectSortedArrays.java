package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {

  @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
  public static List<Integer> intersectTwoSortedArrays(List<Integer> n1, List<Integer> n2) {
    List<Integer> result = new ArrayList<>();

    int i1 = 0;
    int i2 = 0;

    while (i1 < n1.size() && i2 < n2.size()) {
      if (n1.get(i1).equals(n2.get(i2))) {
        while (i1 < n1.size() - 1 && n1.get(i1).equals(n1.get(i1 + 1))) i1++;
        while (i2 < n2.size() - 1 && n2.get(i2).equals(n2.get(i2 + 1))) i2++;
        result.add(n1.get(i1));
        i1++;
        i2++;
      } else if (n1.get(i1) < n2.get(i2)) {
        i1++;
      } else {
        i2++;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntersectSortedArrays.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
