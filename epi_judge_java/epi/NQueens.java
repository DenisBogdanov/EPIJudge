package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    @EpiTest(testDataFile = "n_queens.tsv")
    public static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        recur(0, n, new ArrayList<>(), result);
        return result;
    }

    private static void recur(int currColumn, int n, List<Integer> currCombination, List<List<Integer>> result) {
        if (currColumn == n) {
            result.add(new ArrayList<>(currCombination));
        } else {
            for (int row = 0; row < n; row++) {
                if (ok(currCombination, row, n)) {
                    currCombination.add(row);
                    recur(currColumn + 1, n, currCombination, result);
                    currCombination.remove(currCombination.size() - 1);
                }
            }
        }
    }

    private static boolean ok(List<Integer> currCombination, int row, int n) {
        int currColumn = currCombination.size();
        for (int c = 0; c < currColumn; c++) {
            int r = currCombination.get(c);
            if (r == row) return false;
            if (r - c == row - currColumn) return false;
            if (r + c == row + currColumn) return false;
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
