package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrdering {

    @EpiTest(testDataFile = "spiral_ordering.tsv")
    public static List<Integer> matrixInSpiralOrder(List<List<Integer>> squareMatrix) {
        if (squareMatrix.isEmpty()) return List.of();
        int n = squareMatrix.size();
        List<Integer> ans = new ArrayList<>();
        int right = n - 1;
        int left = 0;
        int top = 0;
        int bottom = n - 1;
        while (ans.size() < n * n) {
            for (int i = left; i <= right; i++) {
                ans.add(squareMatrix.get(top).get(i));
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                ans.add(squareMatrix.get(i).get(right));
            }
            right--;
            for (int i = right; i >= left; i--) {
                ans.add(squareMatrix.get(bottom).get(i));
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                ans.add(squareMatrix.get(i).get(left));
            }
            left++;
        }
        return ans;
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
