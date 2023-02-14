package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixEnclosedRegions {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void fillSurroundedRegions(List<List<Character>> board) {
        int columns = board.get(0).size();

        for (int r = 0; r < board.size(); r++) {
            if (board.get(r).get(0) == 'W') {
                paint(board, r, 0);
            }

            if (board.get(r).get(columns - 1) == 'W') {
                paint(board, r, columns - 1);
            }
        }

        for (int c = 0; c < columns; c++) {
            if (board.get(0).get(c) == 'W') {
                paint(board, 0, c);
            }

            if (board.get(board.size() - 1).get(c) == 'W') {
                paint(board, board.size() - 1, c);
            }
        }

        for (List<Character> row : board) {
            for (int c = 0; c < columns; c++) {
                if (row.get(c) == 'W') {
                    row.set(c, 'B');
                }
            }
        }

        for (List<Character> row : board) {
            for (int c = 0; c < columns; c++) {
                if (row.get(c) == 'C') {
                    row.set(c, 'W');
                }
            }
        }
    }

    private static void paint(List<List<Character>> board, int r, int c) {
        board.get(r).set(c, 'C');

        for (int[] dir : DIRECTIONS) {
            int nextRow = r + dir[0];
            int nextCol = c + dir[1];

            if (nextRow < 0 || nextRow == board.size() || nextCol < 0 || nextCol == board.get(0).size()) continue;
            if (board.get(nextRow).get(nextCol) != 'W') continue;

            paint(board, nextRow, nextCol);
        }
    }

    @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
    public static List<List<Character>> fillSurroundedRegionsWrapper(List<List<Character>> board) {
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
