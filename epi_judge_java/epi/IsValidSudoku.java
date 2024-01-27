package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsValidSudoku {

    @EpiTest(testDataFile = "is_valid_sudoku.tsv")
    // Check if a partially filled matrix has any conflicts.
    public static boolean isValidSudoku(List<List<Integer>> partialAssignment) {
        Set<String> set = new HashSet<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int value = partialAssignment.get(r).get(c);
                if (value > 0) {
                    if (!set.add("Seen " + value + " on " + r + " row")
                            || !set.add("Seen " + value + " on " + c + " column")
                            || !set.add("Seen " + value + " on " + (r / 3 * 3 + c / 3) + " subgrid")) {

                        return false;
                    }
                }
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
