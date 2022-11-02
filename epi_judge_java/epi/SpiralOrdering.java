package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {

    @EpiTest(testDataFile = "spiral_ordering.tsv")
    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        int rows = squareMatrix.size();
        if (rows == 0) return List.of();
        int columns = squareMatrix.get(0).size();

        List<Integer> result = new ArrayList<>(rows * columns);

        int top = 0;
        int right = columns - 1;
        int bottom = rows - 1;
        int left = 0;

        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                result.add(squareMatrix.get(top).get(i));
            }

            top++;

            for (int i = top; i <= bottom; i++) {
                result.add(squareMatrix.get(i).get(right));
            }

            right--;

            for (int i = right; i >= left; i--) {
                result.add(squareMatrix.get(bottom).get(i));
            }

            bottom--;

            for (int i = bottom; i >= top; i--) {
                result.add(squareMatrix.get(i).get(left));
            }

            left++;
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
