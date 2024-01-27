package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class MatrixRotation {

    public static void rotateMatrix(List<List<Integer>> squareMatrix) {
        // 0 1 2        2 1 0        6 3 0
        // 3 4 5    =>  5 4 3    =>  7 4 1
        // 6 7 8        8 7 6        8 5 2

        int n = squareMatrix.size();

        for (List<Integer> row : squareMatrix) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                Collections.swap(row, left++, right--);
            }
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n - r; c++) {
                int temp = squareMatrix.get(r).get(c);
                squareMatrix.get(r).set(c, squareMatrix.get(n - c - 1).get(n - r - 1));
                squareMatrix.get(n - c - 1).set(n - r - 1, temp);
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
