package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchForMinMaxInArray {

  @EpiTest(testDataFile = "search_for_min_max_in_array.tsv")
  public static MinMax findMinMax(List<Integer> list) {
    if (list.size() == 1) {
      return new MinMax(list.get(0), list.get(0));
    }

    MinMax globalMinMax = MinMax.minMax(list.get(0), list.get(1));

    for (int i = 2; i < list.size() - 1; i += 2) {
      MinMax localMinMax = MinMax.minMax(list.get(i), list.get(i + 1));
      globalMinMax = new MinMax(Math.min(globalMinMax.smallest, localMinMax.smallest),
          Math.max(globalMinMax.largest, localMinMax.largest));
    }

    if (list.size() % 2 != 0) {
      globalMinMax = new MinMax(Math.min(globalMinMax.smallest, list.get(list.size() - 1)),
          Math.max(globalMinMax.largest, list.get(list.size() - 1)));
    }

    return globalMinMax;
  }

  @EpiUserType(ctorParams = {Integer.class, Integer.class})
  public static class MinMax {
    public Integer smallest;
    public Integer largest;

    public MinMax(Integer smallest, Integer largest) {
      this.smallest = smallest;
      this.largest = largest;
    }

    private static MinMax minMax(Integer a, Integer b) {
      return b < a ? new MinMax(b, a) : new MinMax(a, b);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      MinMax minMax = (MinMax) o;

      if (!smallest.equals(minMax.smallest)) {
        return false;
      }
      return largest.equals(minMax.largest);
    }

    @Override
    public String toString() {
      return "min: " + smallest + ", max: " + largest;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchForMinMaxInArray.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
