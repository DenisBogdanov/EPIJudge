package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MinimumWeightPathInATriangle {

    @EpiTest(testDataFile = "minimum_weight_path_in_a_triangle.tsv")
    public static int minimumPathTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) return 0;
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int min = Integer.MAX_VALUE;
                if (j > 0) min = triangle.get(i - 1).get(j - 1);
                if (j < triangle.get(i - 1).size()) min = Math.min(min, triangle.get(i - 1).get(j));
                triangle.get(i).set(j, triangle.get(i).get(j) + min);
            }
        }
        return triangle.get(triangle.size() - 1).stream().min(Integer::compare).get();
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
