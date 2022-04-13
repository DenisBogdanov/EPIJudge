package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsStringPalindromicPunctuation {

  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")
  public static boolean isPalindrome(String s) {
    for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
      while (!Character.isLetterOrDigit(s.charAt(left)) && left < right) {
        left++;
      }

      while (!Character.isLetterOrDigit(s.charAt(right)) && left < right) {
        right--;
      }

      if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
