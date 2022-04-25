package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class EnumeratePalindromicDecompositions {

  @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
  public static List<List<String>> palindromeDecompositions(String text) {
    List<List<String>> result = new ArrayList<>();
    solve(text, 0, new ArrayList<>(), result);
    return result;
  }

  private static void solve(String text, int index, List<String> curr, List<List<String>> result) {
    if (index == text.length()) {
      result.add(new ArrayList<>(curr));
    } else {
      for (int i = index + 1; i <= text.length(); i++) {
        if (isPalindrome(text.substring(index, i))) {
          curr.add(text.substring(index, i));
          solve(text, i, curr, result);
          curr.remove(curr.size() - 1);
        }
      }
    }
  }

  private static boolean isPalindrome(String str) {
    for (int i = 0; i < str.length() / 2; i++) {
      if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
        return false;
      }
    }

    return true;
  }

  @EpiTestComparator
  public static boolean comp(List<List<String>> expected,
                             List<List<String>> result) {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EnumeratePalindromicDecompositions.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
