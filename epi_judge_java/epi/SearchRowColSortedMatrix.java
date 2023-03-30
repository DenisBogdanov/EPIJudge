package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchRowColSortedMatrix {

    @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
    public static boolean matrixSearch(List<List<Integer>> matrix, int x) {
        int rows = matrix.size();
        int columns = matrix.get(0).size();

        int currRow = rows - 1;
        int currCol = 0;

        while (currRow >= 0 && currCol < columns) {
            if (matrix.get(currRow).get(currCol) == x) {
                return true;
            } else if (matrix.get(currRow).get(currCol) < x) {
                currCol++;
            } else {
                currRow--;
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
