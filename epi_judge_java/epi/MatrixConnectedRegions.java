package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MatrixConnectedRegions {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void flipColor(int row, int col, List<List<Boolean>> image) {
        int rows = image.size();
        int columns = image.get(0).size();

        boolean currColor = image.get(row).get(col);
        boolean newColor = !currColor;

        Queue<Position> q = new ArrayDeque<>();
        q.offer(new Position(row, col));
        image.get(row).set(col, newColor);

        while (!q.isEmpty()) {
            Position polled = q.poll();

            for (int[] dir : DIRECTIONS) {
                int nextRow = polled.row + dir[0];
                int nextCol = polled.col + dir[1];

                if (nextRow < 0 || nextRow == rows || nextCol < 0 || nextCol == columns) continue;
                if (image.get(nextRow).get(nextCol) != currColor) continue;

                image.get(nextRow).set(nextCol, newColor);
                q.offer(new Position(nextRow, nextCol));
            }
        }
    }

    private static class Position {
        final int row;
        final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    @EpiTest(testDataFile = "painting.tsv")
    public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                       int x, int y,
                                                       List<List<Integer>> image)
            throws Exception {
        List<List<Boolean>> B = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            B.add(new ArrayList<>());
            for (int j = 0; j < image.get(i).size(); j++) {
                B.get(i).add(image.get(i).get(j) == 1);
            }
        }

        executor.run(() -> flipColor(x, y, B));

        image = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            image.add(new ArrayList<>());
            for (int j = 0; j < B.get(i).size(); j++) {
                image.get(i).add(B.get(i).get(j) ? 1 : 0);
            }
        }

        return image;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
