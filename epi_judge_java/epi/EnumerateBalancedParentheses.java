package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumerateBalancedParentheses {

  @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")
  public static List<String> generateBalancedParentheses(int numPairs) {
    List<String> result = new ArrayList<>();
    solve(numPairs, numPairs, "", result);
    return result;
  }

  private static void solve(int leftParenthesesNeeded, int rightParenthesesNeeded, String curr, List<String> result) {
    if (leftParenthesesNeeded == 0 && rightParenthesesNeeded == 0) {
      result.add(curr);
    } else {
      if (leftParenthesesNeeded > 0) {
        solve(leftParenthesesNeeded - 1, rightParenthesesNeeded, curr + "(", result);
      }

      if (leftParenthesesNeeded < rightParenthesesNeeded) {
        solve(leftParenthesesNeeded, rightParenthesesNeeded - 1, curr + ")", result);
      }
    }
  }

  @EpiTestComparator
  public static boolean comp(List<String> expected, List<String> result) {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
