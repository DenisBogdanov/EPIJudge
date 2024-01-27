package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {

    @EpiTest(testDataFile = "spiral_ordering.tsv")
    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        int n = squareMatrix.size();
        int size = n * n;

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        List<Integer> result = new ArrayList<>();

        while (result.size() < size) {
            for (int col = left; col <= right; col++) {
                result.add(squareMatrix.get(top).get(col));
            }

            top++;

            for (int row = top; row <= bottom; row++) {
                result.add(squareMatrix.get(row).get(right));
            }

            right--;

            for (int col = right; col >= left; col--) {
                result.add(squareMatrix.get(bottom).get(col));
            }

            bottom--;

            for (int row = bottom; row >= top; row--) {
                result.add(squareMatrix.get(row).get(left));
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
