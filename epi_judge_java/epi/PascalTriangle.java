package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    @EpiTest(testDataFile = "pascal_triangle.tsv")
    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;

        result.add(List.of(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> prev = result.get(result.size() - 1);
            List<Integer> level = new ArrayList<>();
            level.add(1);
            for (int j = 0; j < prev.size() - 1; j++) {
                level.add(prev.get(j) + prev.get(j + 1));
            }

            level.add(1);
            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
