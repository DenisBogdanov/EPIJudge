package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IsStringInMatrix {
    private static int rows;
    private static int columns;
    private static int n;

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {
        rows = grid.size();
        columns = grid.get(0).size();
        n = pattern.size();

        boolean[][][] deadEnds = new boolean[rows][columns][n];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (helperDfs(grid, r, c, pattern, 0, deadEnds)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean helperDfs(List<List<Integer>> grid, int r, int c, List<Integer> pattern, int index, boolean[][][] deadEnds) {
        if (pattern.size() == index) return true;

        if (r < 0 || r >= rows || c < 0 || c >= columns || deadEnds[r][c][index]) {
            return false;
        }

        if (grid.get(r).get(c).equals(pattern.get(index))
                && (helperDfs(grid, r - 1, c, pattern, index + 1, deadEnds)
                || helperDfs(grid, r + 1, c, pattern, index + 1, deadEnds)
                || helperDfs(grid, r, c - 1, pattern, index + 1, deadEnds)
                || helperDfs(grid, r, c + 1, pattern, index + 1, deadEnds))) {

            return true;
        }

        deadEnds[r][c][index] = true;
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
