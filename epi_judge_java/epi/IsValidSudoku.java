package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsValidSudoku {
    private static final int SIZE = 9;

    @EpiTest(testDataFile = "is_valid_sudoku.tsv")
    // Check if a partially filled matrix has any conflicts.
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        Set<String> seen = new HashSet<>();

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (partialAssignment.get(r).get(c) == 0) continue;

                int value = partialAssignment.get(r).get(c);

                if (!seen.add("Row " + r + " contains " + value)) return false;
                if (!seen.add("Column " + c + " contains " + value)) return false;
                if (!seen.add("SubMatrix " + r / 3 + "x" + c / 3 + " contains " + value)) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidSudoku.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
