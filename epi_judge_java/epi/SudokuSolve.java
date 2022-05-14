package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.*;

public class SudokuSolve {
  private static final int EMPTY_ENTRY = 0;
  private static final int GRID_SIZE = 9;
  private static final int BOX_SIZE = 3;

  public static boolean solveSudoku(List<List<Integer>> partialAssignment) {
    return solveSudoku(0, 0, partialAssignment);
  }

  private static boolean solveSudoku(int row, int col, List<List<Integer>> grid) {
    if (row == GRID_SIZE) {
      row = 0;  // start a new row
      if (++col == GRID_SIZE) {
        return true;
      }
    }

    if (grid.get(row).get(col) != EMPTY_ENTRY) {
      return solveSudoku(row + 1, col, grid);
    }

    for (int value = 1; value <= GRID_SIZE; value++) {
      if (validToAddValue(grid, row, col, value)) {
        grid.get(row).set(col, value);
        if (solveSudoku(row + 1, col, grid)) {
          return true;
        }
      }
    }

    grid.get(row).set(col, EMPTY_ENTRY);  // undo
    return false;
  }

  private static boolean validToAddValue(List<List<Integer>> grid, int row, int col, int value) {
    for (List<Integer> elem : grid) {
      if (value == elem.get(col)) {
        return false;
      }
    }

    for (int c = 0; c < GRID_SIZE; c++) {
      if (value == grid.get(row).get(c)) {
        return false;
      }
    }

    for (int r = 0; r < BOX_SIZE; r++) {
      for (int c = 0; c < BOX_SIZE; c++) {
        if (value == grid.get(row / BOX_SIZE * BOX_SIZE + r).get(col / BOX_SIZE * BOX_SIZE + c)) {
          return false;
        }
      }
    }

    return true;
  }

  @EpiTest(testDataFile = "sudoku_solve.tsv")
  public static void solveSudokuWrapper(TimedExecutor executor,
                                        List<List<Integer>> partialAssignment)
      throws Exception {
    List<List<Integer>> solved = new ArrayList<>();
    for (List<Integer> row : partialAssignment) {
      solved.add(new ArrayList<>(row));
    }

    executor.run(() -> solveSudoku(solved));

    if (partialAssignment.size() != solved.size()) {
      throw new TestFailure("Initial cell assignment has been changed");
    }

    for (int i = 0; i < partialAssignment.size(); i++) {
      List<Integer> br = partialAssignment.get(i);
      List<Integer> sr = solved.get(i);
      if (br.size() != sr.size()) {
        throw new TestFailure("Initial cell assignment has been changed");
      }
      for (int j = 0; j < br.size(); j++)
        if (br.get(j) != 0 && !Objects.equals(br.get(j), sr.get(j)))
          throw new TestFailure("Initial cell assignment has been changed");
    }

    int blockSize = (int) Math.sqrt(solved.size());
    for (int i = 0; i < solved.size(); i++) {
      assertUniqueSeq(solved.get(i));
      assertUniqueSeq(gatherColumn(solved, i));
      assertUniqueSeq(gatherSquareBlock(solved, blockSize, i));
    }
  }

  private static void assertUniqueSeq(List<Integer> seq) throws TestFailure {
    Set<Integer> seen = new HashSet<>();
    for (Integer x : seq) {
      if (x == 0) {
        throw new TestFailure("Cell left uninitialized");
      }
      if (x < 0 || x > seq.size()) {
        throw new TestFailure("Cell value out of range");
      }
      if (seen.contains(x)) {
        throw new TestFailure("Duplicate value in section");
      }
      seen.add(x);
    }
  }

  private static List<Integer> gatherColumn(List<List<Integer>> data, int i) {
    List<Integer> result = new ArrayList<>();
    for (List<Integer> row : data) {
      result.add(row.get(i));
    }
    return result;
  }

  private static List<Integer> gatherSquareBlock(List<List<Integer>> data,
                                                 int blockSize, int n) {
    List<Integer> result = new ArrayList<>();
    int blockX = n % blockSize;
    int blockY = n / blockSize;
    for (int i = blockX * blockSize; i < (blockX + 1) * blockSize; i++) {
      for (int j = blockY * blockSize; j < (blockY + 1) * blockSize; j++) {
        result.add(data.get(i).get(j));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SudokuSolve.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
