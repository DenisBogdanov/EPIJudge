package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchRowColSortedMatrix {

  @EpiTest(testDataFile = "search_row_col_sorted_matrix.tsv")
  public static boolean matrixSearch(List<List<Integer>> matrix, int x) {
    int rows = matrix.size();
    int columns = matrix.get(0).size();

    int row = rows - 1;
    int column = 0;

    while (row >= 0 && column < columns) {
      if (x == matrix.get(row).get(column)) {
        return true;
      } else if (x > matrix.get(row).get(column)) {
        column++;
      } else {
        row--;
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
