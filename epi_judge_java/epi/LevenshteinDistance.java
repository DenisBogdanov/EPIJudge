package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class LevenshteinDistance {

  @EpiTest(testDataFile = "levenshtein_distance.tsv")
  public static int levenshteinDistance(String a, String b) {
    int[][] distanceBetweenPrefixes = new int[a.length()][b.length()];
    for (int[] row : distanceBetweenPrefixes) {
      Arrays.fill(row, -1);
    }
    return solve(a, a.length() - 1, b, b.length() - 1, distanceBetweenPrefixes);
  }

  private static int solve(String a, int aIdx, String b, int bIdx, int[][] distanceBetweenPrefixes) {
    if (aIdx < 0) {
      return bIdx + 1;
    } else if (bIdx < 0) {
      return aIdx + 1;
    }

    if (distanceBetweenPrefixes[aIdx][bIdx] == -1) {
      if (a.charAt(aIdx) == b.charAt(bIdx)) {
        distanceBetweenPrefixes[aIdx][bIdx] = solve(a, aIdx - 1, b, bIdx - 1, distanceBetweenPrefixes);
      } else {
        int substituteLast = solve(a, aIdx - 1, b, bIdx - 1, distanceBetweenPrefixes);
        int addLast = solve(a, aIdx, b, bIdx - 1, distanceBetweenPrefixes);
        int deleteLast = solve(a, aIdx - 1, b, bIdx, distanceBetweenPrefixes);
        distanceBetweenPrefixes[aIdx][bIdx] = 1 + Math.min(substituteLast, Math.min(addLast, deleteLast));
      }
    }

    return distanceBetweenPrefixes[aIdx][bIdx];
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
