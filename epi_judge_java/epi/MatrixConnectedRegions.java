package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MatrixConnectedRegions {
    private static int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void flipColor(int x, int y, List<List<Boolean>> image) {
        int rows = image.size();
        int columns = image.get(0).size();

        boolean newColor = !image.get(x).get(y);

        Queue<Coordinate> q = new ArrayDeque<>();
        q.offer(new Coordinate(x, y));
        image.get(x).set(y, null);

        while (!q.isEmpty()) {
            Coordinate polled = q.poll();

            for (int[] direction : DIRECTIONS) {
                int newX = direction[0] + polled.r;
                int newY = direction[1] + polled.c;

                if (newX < 0 || newX >= rows || newY < 0 || newY >= columns) continue;
                if (image.get(newX).get(newY) == null || image.get(newX).get(newY) == newColor) continue;

                image.get(newX).set(newY, newColor);
                q.offer(new Coordinate(newX, newY));
            }
        }

        for (List<Boolean> row : image) {
            for (int i = 0; i < columns; i++) {
                if (row.get(i) == null) row.set(i, newColor);
            }
        }
    }

    private static class Coordinate {
        final int r;
        final int c;

        public Coordinate(int r, int c) {
            this.r = r;
            this.c = c;
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
