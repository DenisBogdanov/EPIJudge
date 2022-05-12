package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixEnclosedRegions {
  private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public static void fillSurroundedRegions(List<List<Character>> board) {
    int rows = board.size();
    int columns = board.get(0).size();

    for (int col = 0; col < columns; col++) {
      if (board.get(0).get(col) == 'W') {
        board.get(0).set(col, '#');
        dfs(board, 0, col);
      }

      if (board.get(rows - 1).get(col) == 'W') {
        board.get(rows - 1).set(col, '#');
        dfs(board, rows - 1, col);
      }
    }

    for (int row = 0; row < rows; row++) {
      if (board.get(row).get(0) == 'W') {
        board.get(row).set(0, '#');
        dfs(board, row, 0);
      }

      if (board.get(row).get(columns - 1) == 'W') {
        board.get(row).set(columns - 1, '#');
        dfs(board, row, columns - 1);
      }
    }

    for (List<Character> row : board) {
      for (int col = 0; col < columns; col++) {
        if (row.get(col) == 'W') {
          row.set(col, 'B');
        }
      }
    }

    for (List<Character> row : board) {
      for (int col = 0; col < columns; col++) {
        if (row.get(col) == '#') {
          row.set(col, 'W');
        }
      }
    }
  }

  private static void dfs(List<List<Character>> board, int x, int y) {
    for (int[] dir : DIRECTIONS) {
      int nextRow = x + dir[0];
      int nextCol = y + dir[1];

      if (nextRow < 0 || nextRow == board.size() || nextCol < 0 || nextCol == board.get(0).size()) {
        continue;
      }

      if (board.get(nextRow).get(nextCol) == 'W') {
        board.get(nextRow).set(nextCol, '#');
        dfs(board, nextRow, nextCol);
      }
    }
  }

  @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
  public static List<List<Character>>
  fillSurroundedRegionsWrapper(List<List<Character>> board) {
    fillSurroundedRegions(board);
    return board;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
