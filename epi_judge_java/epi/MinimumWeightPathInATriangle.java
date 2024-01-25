package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MinimumWeightPathInATriangle {

    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    public static int minimumPathTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) return 0;
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> currRow = triangle.get(i);

            currRow.set(0, currRow.get(0) + prevRow.get(0));
            currRow.set(currRow.size() - 1, currRow.get(currRow.size() - 1) + prevRow.get(prevRow.size() - 1));

            for (int j = 1; j < currRow.size() - 1; j++) {
                currRow.set(j, Math.min(prevRow.get(j - 1), prevRow.get(j)) + currRow.get(j));
            }
        }

        return triangle.get(triangle.size() - 1).stream().mapToInt(Integer::intValue).min().orElseThrow();
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
