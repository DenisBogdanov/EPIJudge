package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidIpAddresses {
  private final static String DOT = ".";

  @EpiTest(testDataFile = "valid_ip_addresses.tsv")
  public static List<String> getValidIpAddress(String s) {
    List<String> result = new ArrayList<>();

    if (s.length() < 4) {
      return result;
    }

    for (int i = 1; i < 4 && i < s.length(); i++) {
      String firstPart = s.substring(0, i);
      if (isValid(firstPart)) {

        for (int j = 1; i + j < s.length() && j < 4; j++) {
          String secondPart = s.substring(i, i + j);

          if (isValid(secondPart)) {
            for (int k = 1; i + k + j < s.length() && k < 4; k++) {
              String thirdPart = s.substring(i + j, i + j + k);

              if (isValid(thirdPart)) {
                String fourthPart = s.substring(i + j + k);
                if (isValid(fourthPart)) {

                  result.add(firstPart + DOT + secondPart + DOT + thirdPart + DOT + fourthPart);
                }
              }
            }
          }
        }
      }
    }

    return result;
  }

  private static boolean isValid(String s) {
    if (s.length() > 3) {
      return false;
    }

    if (s.startsWith("0") && s.length() > 1) {
      return false;
    }

    return Integer.parseInt(s) < 256;
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
            .runFromAnnotations(args, "ValidIpAddresses.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
