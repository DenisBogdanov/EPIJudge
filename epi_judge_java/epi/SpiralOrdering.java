package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {

  @EpiTest(testDataFile = "spiral_ordering.tsv")
  public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
    int size = squareMatrix.size();
    List<Integer> result = new ArrayList<>(size * size);

    int layers = (size + 1) / 2;

    for (int layer = 0; layer < layers; layer++) {
      int layerLength = size - 1 - layer * 2;

      // upper row
      for (int j = 0; j < layerLength; j++) {
        result.add(squareMatrix.get(layer).get(j + layer));
      }

      // right column
      for (int j = 0; j < layerLength; j++) {
        result.add(squareMatrix.get(j + layer).get(size - layer - 1));
      }

      // bottom row
      for (int j = 0; j < layerLength; j++) {
        result.add(squareMatrix.get(size - layer - 1).get(size - layer - 1 - j));
      }

      // left column
      for (int j = 0; j < layerLength; j++) {
        result.add(squareMatrix.get(size - layer - 1 - j).get(layer));
      }
    }

    if (size % 2 == 1) {
      result.add(squareMatrix.get(layers - 1).get(layers - 1));
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpiralOrdering.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
