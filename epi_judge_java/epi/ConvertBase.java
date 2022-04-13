package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {

  @EpiTest(testDataFile = "convert_base.tsv")
  public static String convertBase(String numAsString, int b1, int b2) {
    if (b1 == b2 || numAsString.equals("0")) {
      return numAsString;
    }

    int firstDigitIndex = 0;

    if (numAsString.charAt(0) == '-' || numAsString.charAt(0) == '+') {
      firstDigitIndex = 1;
    }

    int decimal = toInt(numAsString, b1, firstDigitIndex);

    StringBuilder result = decimalToBase(decimal, b2);

    return result
        .append(firstDigitIndex == 1 ? numAsString.charAt(0) : "")
        .reverse().toString();
  }

  private static StringBuilder decimalToBase(int decimal, int b2) {
    StringBuilder result = new StringBuilder();
    while (decimal > 0) {
      int digit = decimal % b2;
      if (digit <= 9) {
        result.append(digit);
      } else {
        result.append((char) ('A' + digit - 10));
      }
      decimal /= b2;
    }
    return result;
  }

  private static int toInt(String numAsString, int b1, int firstDigitIndex) {
    int decimal = 0;
    int pow = 1;

    for (int i = numAsString.length() - 1; i >= firstDigitIndex; i--) {
      char c = numAsString.charAt(i);
      decimal += pow * (c <= '9' ? c - '0' : c - 'A' + 10);
      pow *= b1;
    }
    return decimal;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
