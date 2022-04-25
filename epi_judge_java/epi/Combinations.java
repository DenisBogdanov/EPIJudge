package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

  @EpiTest(testDataFile = "combinations.tsv")
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    solve(1, n, k, new ArrayList<>(), result);
    return result;
  }

  private static void solve(int offset, int limit, int size, List<Integer> partialArrangement, List<List<Integer>> result) {
    if (size == partialArrangement.size()) {
      result.add(new ArrayList<>(partialArrangement));
    } else {
      for (int i = offset; i <= limit; i++) {
        partialArrangement.add(i);
        solve(i + 1, limit, size, partialArrangement, result);
        partialArrangement.remove(partialArrangement.size() - 1);
      }
    }
  }

  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Combinations.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
