package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixEnclosedRegions {
    private static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
    };

    public static void fillSurroundedRegions(List<List<Character>> board) {
        int n = board.size();
        int m = board.get(0).size();

        boolean[][] notEnclosed = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            if (board.get(i).get(0) == 'W') {
                findNotEnclosedDfs(board, n, m, notEnclosed, i, 0);
            }
            if (board.get(i).get(m - 1) == 'W') {
                findNotEnclosedDfs(board, n, m, notEnclosed, i, m - 1);
            }
        }
        for (int j = 1; j < m - 1; j++) {
            if (board.get(0).get(j) == 'W') {
                findNotEnclosedDfs(board, n, m, notEnclosed, 0, j);
            }
            if (board.get(n - 1).get(j) == 'W') {
                findNotEnclosedDfs(board, n, m, notEnclosed, n - 1, j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!notEnclosed[i][j]) {
                    board.get(i).set(j, 'B');
                }
            }
        }
    }

    private static void findNotEnclosedDfs(List<List<Character>> board, int n, int m, boolean[][] notEnclosed, int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m) return;
        if (board.get(r).get(c) == 'B' || notEnclosed[r][c]) return;
        notEnclosed[r][c] = true;
        for (var dir : DIRS) {
            findNotEnclosedDfs(board, n, m, notEnclosed, r + dir[0], c + dir[1]);
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
