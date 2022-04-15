package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")
  public static List<List<Integer>> generatePascalTriangle(int numRows) {
    if (numRows < 1) {
      return List.of();
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> prevList = List.of(1);
    result.add(prevList);

    for (int i = 1; i < numRows; i++) {
      List<Integer> currentList = new ArrayList<>(prevList.size() + 1);
      currentList.add(1);

      for (int j = 0; j < prevList.size() - 1; j++) {
        currentList.add(prevList.get(j) + prevList.get(j + 1));
      }

      currentList.add(1);
      result.add(currentList);
      prevList = currentList;
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
