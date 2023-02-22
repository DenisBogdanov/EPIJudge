package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MinimumWeightPathInATriangle {

    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    public static int minimumPathTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) return 0;

        int n = triangle.size();
        for (int i = 1; i < n; i++) {
            List<Integer> row = triangle.get(i);
            List<Integer> prevRow = triangle.get(i - 1);
            int size = row.size();

            row.set(0, row.get(0) + prevRow.get(0));
            row.set(size - 1, row.get(size - 1) + prevRow.get(size - 2));

            for (int j = 1; j < size - 1; j++) {
                row.set(j, row.get(j) + Math.min(prevRow.get(j - 1), prevRow.get(j)));
            }
        }

        int result = Integer.MAX_VALUE;
        for (Integer num : triangle.get(n - 1)) {
            result = Math.min(result, num);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumWeightPathInATriangle.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
