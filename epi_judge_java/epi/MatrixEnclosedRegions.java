package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixEnclosedRegions {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void fillSurroundedRegions(List<List<Character>> board) {
        int rows = board.size();
        int columns = board.get(0).size();

        boolean[][] opened = new boolean[rows][columns];
        for (int r = 0; r < rows; r++) {
            if (board.get(r).get(0) == 'W' && !opened[r][0]) {
                opened[r][0] = true;
                findOpenedDfs(board, r, 0, opened);
            }

            if (board.get(r).get(columns - 1) == 'W' && !opened[r][columns - 1]) {
                opened[r][columns - 1] = true;
                findOpenedDfs(board, r, columns - 1, opened);
            }
        }

        for (int c = 1; c < columns - 1; c++) {
            if (board.get(0).get(c) == 'W' && !opened[0][c]) {
                opened[0][c] = true;
                findOpenedDfs(board, 0, c, opened);
            }

            if (board.get(rows - 1).get(c) == 'W' && !opened[rows - 1][c]) {
                opened[rows - 1][c] = true;
                findOpenedDfs(board, rows - 1, c, opened);
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!opened[r][c]) {
                    board.get(r).set(c, 'B');
                }
            }
        }
    }

    private static void findOpenedDfs(List<List<Character>> board, int r, int c, boolean[][] opened) {
        int rows = board.size();
        int columns = board.get(0).size();

        for (int[] dir : DIRECTIONS) {
            int nextRow = r + dir[0];
            int nextCol = c + dir[1];

            if (nextRow < 0 || nextRow == rows || nextCol < 0 || nextCol == columns) continue;
            if (board.get(r).get(c) == 'B' || opened[nextRow][nextCol]) continue;

            opened[nextRow][nextCol] = true;
            findOpenedDfs(board, nextRow, nextCol, opened);
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
