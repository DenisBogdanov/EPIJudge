package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    @EpiTest(testDataFile = "n_queens.tsv")
    public static List<List<Integer>> nQueens(int n) {
        if (n == 1) return Arrays.asList((Arrays.asList(0)));
        if (n < 4) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        recur(n, 0, new ArrayList<>(), result);
        return result;
    }

    private static void recur(int n, int currRow, List<Integer> currArrangement, List<List<Integer>> result) {
        if (currRow == n) {
            result.add(new ArrayList<>(currArrangement));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (satisfies(currRow, col, currArrangement)) {
                currArrangement.add(col);
                recur(n, currRow + 1, currArrangement, result);
                currArrangement.remove(currArrangement.size() - 1);
            }
        }
    }

    private static boolean satisfies(int row, int col, List<Integer> currArrangement) {
        if (currArrangement.contains(col)) return false;
        for (int r = 0; r < currArrangement.size(); r++) {
            if (r - currArrangement.get(r) == row - col) return false;
            if (r + currArrangement.get(r) == row + col) return false;
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
