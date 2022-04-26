package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class IsStringInMatrix {

  @EpiTest(testDataFile = "is_string_in_matrix.tsv")
  public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {
    Set<Attempt> previousAttempts = new HashSet<>();
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).size(); j++) {
        if (containsPattern(grid, i, j, pattern, 0, previousAttempts)) {
          return true;
        }
      }
    }

    return false;
  }

  private static boolean containsPattern(List<List<Integer>> grid, int row, int col, List<Integer> pattern,
                                         int offset, Set<Attempt> previousAttempts) {

    if (pattern.size() == offset) {
      return true;
    }

    if (row < 0 || row >= grid.size() || col < 0 || col >= grid.get(row).size()
        || previousAttempts.contains(new Attempt(row, col, offset))) {

      return false;
    }

    if (grid.get(row).get(col).equals(pattern.get(offset))
        && (containsPattern(grid, row + 1, col, pattern, offset + 1, previousAttempts)
        || containsPattern(grid, row - 1, col, pattern, offset + 1, previousAttempts)
        || containsPattern(grid, row, col + 1, pattern, offset + 1, previousAttempts)
        || containsPattern(grid, row, col - 1, pattern, offset + 1, previousAttempts))) {

      return true;
    }

    previousAttempts.add(new Attempt(row, col, offset));
    return false;
  }

  private static class Attempt {
    final Integer x;
    final Integer y;
    final Integer offset;

    public Attempt(Integer x, Integer y, Integer offset) {
      this.x = x;
      this.y = y;
      this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Attempt cacheEntry = (Attempt) o;

      return Objects.equals(x, cacheEntry.x)
          && Objects.equals(y, cacheEntry.y)
          && Objects.equals(offset, cacheEntry.offset);
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y, offset);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringInMatrix.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
