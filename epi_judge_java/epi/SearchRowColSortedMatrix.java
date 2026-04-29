package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchRowColSortedMatrix {

    @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
    public static boolean matrixSearch(List<List<Integer>> grid, int x) {
        int r = grid.size() - 1;
        int c = 0;
        while (r >= 0 && c < grid.get(0).size()) {
            if (x == grid.get(r).get(c)) return true;
            else if (x < grid.get(r).get(c)) r--;
            else c++;
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
