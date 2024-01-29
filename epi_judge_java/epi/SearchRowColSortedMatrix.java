package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchRowColSortedMatrix {

    @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
    public static boolean matrixSearch(List<List<Integer>> grid, int x) {
        int rows = grid.size();
        int columns = grid.get(0).size();

        int r = rows - 1;
        int c = 0;

        while (r >= 0 && c < columns) {
            int num = grid.get(r).get(c);
            if (num == x) return true;

            if (num < x) {
                c++;
            } else {
                r--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchRowColSortedMatrix.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
