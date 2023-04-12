package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class MatrixRotation {

    public static void rotateMatrix(List<List<Integer>> squareMatrix) {
        int n = squareMatrix.size();

        // vertical mirroring
        for (List<Integer> row : squareMatrix) {
            for (int i = 0; i < n / 2; i++) {
                Collections.swap(row, i, n - i - 1);
            }
        }

        // diagonal mirroring
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n - r - 1; c++) {
                int temp = squareMatrix.get(r).get(c);
                squareMatrix.get(r).set(c, squareMatrix.get(n - c - 1).get(n - r - 1));
                squareMatrix.get(n - c - 1).set(n - r - 1, temp);
            }
        }
    }

    @EpiTest(testDataFile = "matrix_rotation.tsv")
    public static List<List<Integer>>
    rotateMatrixWrapper(List<List<Integer>> squareMatrix) {
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
