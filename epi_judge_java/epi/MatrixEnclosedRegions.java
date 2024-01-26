package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixEnclosedRegions {
    private static int rows;
    private static int columns;

    public static void fillSurroundedRegions(List<List<Character>> board) {
        rows = board.size();
        columns = board.get(0).size();

        for (int c = 0; c < columns; c++) {
            if (board.get(0).get(c) == 'W') paint(0, c, board);
            if (board.get(rows - 1).get(c) == 'W') paint(rows - 1, c, board);
        }

        for (int r = 1; r < rows - 1; r++) {
            if (board.get(r).get(0) == 'W') paint(r, 0, board);
            if (board.get(r).get(columns - 1) == 'W') paint(r, columns - 1, board);
        }

        for (List<Character> row : board) {
            for (int c = 0; c < columns; c++) {
                if (row.get(c) == 'W') row.set(c, 'B');
                else if (row.get(c) == '#') row.set(c, 'W');
            }
        }
    }

    private static void paint(int r, int c, List<List<Character>> board) {
        if (r < 0 || r >= rows || c < 0 || c >= columns) return;
        if (board.get(r).get(c) != 'W') return;

        board.get(r).set(c, '#');

        paint(r + 1, c, board);
        paint(r - 1, c, board);
        paint(r, c + 1, board);
        paint(r, c - 1, board);
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
