package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class MatrixConnectedRegions {
    private static final int[][] DIRS = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
    };

    public static void flipColor(int x, int y, List<List<Boolean>> image) {
        int n = image.size();
        int m = image.get(0).size();
        boolean currColor = image.get(x).get(y);
        dfs(image, n, m, currColor, x, y);
    }

    private static void dfs(List<List<Boolean>> image, int n, int m, boolean currColor, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n) return;
        if (image.get(x).get(y) != currColor) return;
        image.get(x).set(y, !currColor);
        for (var dir : DIRS) {
            dfs(image, n, m, currColor, x + dir[0], y + dir[1]);
        }
    }

    @EpiTest(testDataFile = "painting.tsv")
    public static List<List<Integer>> flipColorWrapper(TimedExecutor executor, int x, int y,
                                                       List<List<Integer>> image) throws Exception {
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
