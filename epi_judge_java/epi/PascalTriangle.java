package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangle {

    @EpiTest(testDataFile = "pascal_triangle.tsv")
    public static List<List<Integer>> generatePascalTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) return result;

        result.add(new ArrayList<>(Arrays.asList(1)));

        for (int i = 1; i < numRows; i++) {
            List<Integer> currRow = new ArrayList<>();
            currRow.add(1);

            List<Integer> prevRow = result.get(i - 1);
            for (int j = 0; j < prevRow.size() - 1; j++) {
                currRow.add(prevRow.get(j) + prevRow.get(j + 1));
            }

            currRow.add(1);

            result.add(currRow);
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
