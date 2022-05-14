package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsValidSudoku {
  private static final int GRID_SIZE = 9;
  private static final int SUB_GRID_SIZE = 3;
  private static final int SUB_GRID_COUNT = 3;


  @EpiTest(testDataFile = "is_valid_sudoku.tsv")
  // Check if a partially filled matrix has any conflicts.
  public static boolean isValidSudoku(List<List<Integer>> grid) {
//    return isValidHorizontally(grid)
//        && isValidVertically(grid)
//        && validSubGrids(grid);


    Set<String> seen = new HashSet<>();
    for (int row = 0; row < grid.size(); row++) {
      for (int col = 0; col < grid.get(row).size(); col++) {
        Integer value = grid.get(row).get(col);
        if (value != null && value != 0) {
          if (!seen.add(value + " on row=" + row)
              || !seen.add(value + " on column=" + col)
              || !seen.add(value + " in the box(" + (row / 3) + "," + (col / 3) + ")")) {

            return false;
          }
        }
      }
    }

    return true;
  }

  private static boolean validSubGrids(List<List<Integer>> partialAssignment) {
    List<Integer> kit = new ArrayList<>(GRID_SIZE);
    for (int i = 0; i < SUB_GRID_COUNT; i++) {

      for (int j = 0; j < SUB_GRID_COUNT; j++) {

        kit.clear();
        for (int k = 0; k < SUB_GRID_SIZE; k++) {
          for (int m = 0; m < SUB_GRID_SIZE; m++) {
            kit.add(partialAssignment.get(i * SUB_GRID_SIZE + k).get(j * SUB_GRID_SIZE + m));
          }
        }

        if (!isValidKit(kit)) {
          return false;
        }
      }
    }

    return true;
  }

  private static boolean isValidVertically(List<List<Integer>> partialAssignment) {
    List<Integer> kit = new ArrayList<>(GRID_SIZE);
    for (int column = 0; column < GRID_SIZE; column++) {
      kit.clear();
      for (int row = 0; row < GRID_SIZE; row++) {
        kit.add(partialAssignment.get(row).get(column));
      }
      if (!isValidKit(kit)) {
        return false;
      }
    }

    return true;
  }

  private static boolean isValidHorizontally(List<List<Integer>> partialAssignment) {
    for (List<Integer> row : partialAssignment) {
      if (!isValidKit(row)) {
        return false;
      }
    }

    return true;
  }

  private static boolean isValidKit(List<Integer> kit) {
    Set<Integer> prev = new HashSet<>();
    for (Integer num : kit) {
      if (prev.contains(num)) {
        return false;
      }

      if (num != 0) {
        prev.add(num);
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
