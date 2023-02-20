package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class IsStringInMatrix {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {
        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid.get(0).size(); c++) {
                if (contains(grid, r, c, pattern, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean contains(List<List<Integer>> grid, int r, int c, List<Integer> pattern, int index) {
        if (!grid.get(r).get(c).equals(pattern.get(index))) {
            return false;
        } else if (index == pattern.size() - 1) {
            return true;
        }

        for (int[] dir : DIRECTIONS) {
            int nextRow = r + dir[0];
            int nextCol = c + dir[1];

            if (nextRow < 0 || nextRow == grid.size() || nextCol < 0 || nextCol == grid.get(0).size()) continue;
            if (contains(grid, nextRow, nextCol, pattern, index + 1)) {
                return true;
            }
        }

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
