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
        List<List<Integer>> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(List.of(0));
            return ans;
        }
        if (n <= 3) return ans;
        List<Integer> currPosition = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            currPosition.add(-1);
        }
        recur(0, n, currPosition, ans);
        return ans;
    }

    private static void recur(int row, int n, List<Integer> currPosition, List<List<Integer>> ans) {
        if (row == n) {
            ans.add(new ArrayList<>(currPosition));
            return;
        }
        for (int col = 0; col < n; col++) {
            currPosition.set(row, col);
            if (isValid(row, n, currPosition)) {
                recur(row + 1, n, currPosition, ans);
            }
        }
    }

    private static boolean isValid(int row, int n, List<Integer> currPosition) {
        int latestCol = currPosition.get(row);
        for (int i = 0; i < row; i++) {
            if (currPosition.get(i) == latestCol) return false;
            if (currPosition.get(i) - i == latestCol - row) return false;
            if (currPosition.get(i) + i == latestCol + row) return false;
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
