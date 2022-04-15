package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;

public class RomanToInteger {
  private static final Map<Character, Integer> ROMAN_TO_ARABIC_MAP = Map.of(
      'I', 1,
      'V', 5,
      'X', 10,
      'L', 50,
      'C', 100,
      'D', 500,
      'M', 1_000);

  @EpiTest(testDataFile = "roman_to_integer.tsv")
  public static int romanToInteger(String s) {
    int result = ROMAN_TO_ARABIC_MAP.get(s.charAt(s.length() - 1));

    for (int i = s.length() - 2; i >= 0; i--) {
      int next = ROMAN_TO_ARABIC_MAP.get(s.charAt(i + 1));
      int prev = ROMAN_TO_ARABIC_MAP.get(s.charAt(i));

      if (prev < next) {
        result -= prev;
      } else {
        result += prev;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RomanToInteger.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
