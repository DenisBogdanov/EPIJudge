package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NQueens {

  @EpiTest(testDataFile = "n_queens.tsv")
  public static List<List<Integer>> nQueens(int n) {
    List<List<Integer>> result = new ArrayList<>();
    solve(n, 0, new LinkedList<>(), result);
    return result;
  }

  private static void solve(int n, int row, List<Integer> partialArrangement, List<List<Integer>> result) {
    if (row == n) {
      result.add(new ArrayList<>(partialArrangement));
    } else {
      for (int col = 0; col < n; col++) {
        partialArrangement.add(col);
        if (isValid(partialArrangement)) {
          solve(n, row + 1, partialArrangement, result);
        }

        partialArrangement.remove(partialArrangement.size() - 1);
      }
    }
  }

  private static boolean isValid(List<Integer> partialArrangement) {
    int lastRow = partialArrangement.size() - 1;
    for (int i = 0; i < lastRow; i++) {
      int diff = Math.abs(partialArrangement.get(i) - partialArrangement.get(lastRow));
      if (diff == 0 || diff == lastRow - i) {
        return false;
      }
    }

    return true;
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
            .runFromAnnotations(args, "NQueens.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
