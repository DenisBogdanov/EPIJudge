package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

  public static String intToString(int x) {
    return x + "";
  }

  public static int stringToInt(String s) {
    int result = 0;

    int sign = 1;
    int startIndex = 0;

    if (s.charAt(0) == '-' || s.charAt(0) == '+') {
      startIndex = 1;
      sign = s.charAt(0) == '-' ? -1 : 1;
    }

    for (int i = startIndex; i < s.length(); i++) {
      char digit = s.charAt(i);
      if (digit >= '0' && digit <= '9') {
        result *= 10;
        result += (digit - '0');
      } else {
        throw new NumberFormatException("Unexpected character: '" + digit + "'");
      }
    }

    return result * sign;
  }

  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (Integer.parseInt(intToString(x)) != x) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
