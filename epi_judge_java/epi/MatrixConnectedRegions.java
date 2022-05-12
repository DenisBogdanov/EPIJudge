package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class MatrixConnectedRegions {
  private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public static void flipColor(int x, int y, List<List<Boolean>> image) {
    boolean initialColor = image.get(x).get(y);
    image.get(x).set(y, !initialColor);

    for (int[] dir : DIRECTIONS) {
      int nextRow = x + dir[0];
      int nextCol = y + dir[1];

      if (nextRow < 0 || nextRow == image.size() || nextCol < 0 || nextCol == image.get(0).size()) {
        continue;
      }

      if (image.get(nextRow).get(nextCol) == initialColor) {
        flipColor(nextRow, nextCol, image);
      }
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
