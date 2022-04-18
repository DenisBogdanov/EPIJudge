package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SubstringMatch {

  @EpiTest(testDataFile = "substring_match.tsv")
  // Returns the index of the first character of the substring if found, -1
  // otherwise.
  public static int rabinKarp(String str, String subStr) {
    if (subStr.length() > str.length()) {
      return -1;
    }

    final int base = 26;
    int strHash = 0;
    int subStrHash = 0;
    int power = 1;

    for (int i = 0; i < subStr.length(); i++) {
      power = i > 0 ? power * base : 1;
      strHash = strHash * base + str.charAt(i);
      subStrHash = subStrHash * base + subStr.charAt(i);
    }

    for (int i = subStr.length(); i < str.length(); i++) {
      if (strHash == subStrHash && str.startsWith(subStr, i - subStr.length())) {
        return i - subStr.length();
      }

      strHash -= str.charAt(i - subStr.length()) * power;
      strHash = strHash * base + str.charAt(i);
    }

    if (strHash == subStrHash && str.endsWith(subStr)) {
      return str.length() - subStr.length();
    }

    return -1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SubstringMatch.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
