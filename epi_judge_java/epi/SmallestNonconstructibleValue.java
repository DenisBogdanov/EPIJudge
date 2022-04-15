package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallestNonconstructibleValue {

  @EpiTest(testDataFile = "smallest_nonconstructible_value.tsv")
  public static int smallestNonconstructibleValue(List<Integer> list) {
    Collections.sort(list);

    int maxConstructibleValue = 0;
    for (Integer num : list) {
      if (num > maxConstructibleValue + 1) {
        return maxConstructibleValue + 1;
      }

      maxConstructibleValue += num;
    }

    return maxConstructibleValue + 1;
  }

  public static void main(String[] args) {
    System.out.println(smallestNonconstructibleValue(new ArrayList<>(List.of(1, 3, 2, 1))));

    System.exit(
        GenericTest
            .runFromAnnotations(args, "SmallestNonconstructibleValue.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
