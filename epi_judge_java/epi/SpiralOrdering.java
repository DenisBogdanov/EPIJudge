package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {

    @EpiTest(testDataFile = "spiral_ordering.tsv")
    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        List<Integer> result = new ArrayList<>();

        int rows = squareMatrix.size();
        if (rows == 0) return result;

        int columns = squareMatrix.get(0).size();

        int leftCol = 0;
        int rightCol = columns - 1;
        int topRow = 0;
        int bottomRow = rows - 1;

        while (result.size() < rows * columns) {
            for (int c = leftCol; c <= rightCol; c++) {
                result.add(squareMatrix.get(topRow).get(c));
            }

            topRow++;

            for (int r = topRow; r <= bottomRow; r++) {
                result.add(squareMatrix.get(r).get(rightCol));
            }

            rightCol--;

            for (int c = rightCol; c >= leftCol; c--) {
                result.add(squareMatrix.get(bottomRow).get(c));
            }

            bottomRow--;

            for (int r = bottomRow; r >= topRow; r--) {
                result.add(squareMatrix.get(r).get(leftCol));
            }

            leftCol++;
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
