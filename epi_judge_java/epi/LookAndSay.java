package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class LookAndSay {

  @EpiTest(testDataFile = "look_and_say.tsv")
  public static String lookAndSay(int n) {
    String result = "1";

    for (int i = 0; i < n - 1; i++) {
      result = next(result);
    }

    return result;
  }

  private static String next(String str) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      int count = 1;
      while (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
        ++i;
        ++count;
      }

      result.append(count)
          .append(str.charAt(i));
    }

    return result.toString();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LookAndSay.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
