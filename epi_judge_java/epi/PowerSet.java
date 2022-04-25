package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerSet {
  private static final double LOG_2 = Math.log(2);

  @EpiTest(testDataFile = "power_set.tsv")
  public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
//    return bitMaskSolution(inputSet);


    List<List<Integer>> result = new ArrayList<>();
    recursivePowerSet(inputSet, 0, new ArrayList<>(), result);
    return result;
  }

  private static void recursivePowerSet(List<Integer> inputSet, int toBeSelected,
                                        List<Integer> selectedSoFar, List<List<Integer>> result) {

    if (toBeSelected == inputSet.size()) {
      result.add(new ArrayList<>(selectedSoFar));
      return;
    }

    selectedSoFar.add(inputSet.get(toBeSelected));
    recursivePowerSet(inputSet, toBeSelected + 1, selectedSoFar, result);
    selectedSoFar.remove(selectedSoFar.size() - 1);
    recursivePowerSet(inputSet, toBeSelected + 1, selectedSoFar, result);
  }

  private static List<List<Integer>> bitMaskSolution(List<Integer> inputSet) {
    List<List<Integer>> result = new ArrayList<>();

    for (int intForSubset = 0; intForSubset < (1 << inputSet.size()); intForSubset++) {
      int bitArray = intForSubset;
      List<Integer> subset = new ArrayList<>();

      while (bitArray != 0) {
        subset.add(inputSet.get((int) (Math.log(bitArray & -bitArray) / LOG_2)));
        bitArray &= (bitArray - 1);
      }

      result.add(subset);
    }

    return result;
  }

  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected, List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PowerSet.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
