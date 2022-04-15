package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MatrixRotation {

  public static void rotateMatrix(List<List<Integer>> squareMatrix) {
    final int maxIndex = squareMatrix.size() - 1;

    for (int layer = 0; layer < squareMatrix.size() / 2; ++layer) {
      for (int j = layer; j < maxIndex - layer; ++j) {
        int temp1 = squareMatrix.get(maxIndex - j).get(layer);
        int temp2 = squareMatrix.get(maxIndex - layer).get(maxIndex - j);
        int temp3 = squareMatrix.get(j).get(maxIndex - layer);
        int temp4 = squareMatrix.get(layer).get(j);

        squareMatrix.get(layer).set(j, temp1);
        squareMatrix.get(maxIndex - j).set(layer, temp2);
        squareMatrix.get(maxIndex - layer).set(maxIndex - j, temp3);
        squareMatrix.get(j).set(maxIndex - layer, temp4);
      }
    }
  }

  @EpiTest(testDataFile = "matrix_rotation.tsv")
  public static List<List<Integer>> rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
    rotateMatrix(squareMatrix);
    return squareMatrix;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixRotation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
