package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsNumberPalindromic {
  @EpiTest(testDataFile = "is_number_palindromic.tsv")
  public static boolean isPalindromeNumber(int x) {
    if (x < 0) {
      return false;
    }

    int current = x;
    int reverse = 0;

    while (current != 0) {
      reverse *= 10;
      reverse += current % 10;
      current /= 10;
    }

    return x == reverse;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
