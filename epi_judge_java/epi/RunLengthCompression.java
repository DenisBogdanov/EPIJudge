package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

  public static String decoding(String s) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      int count = 0;
      while (Character.isDigit(s.charAt(i))) {
        count = count * 10 + s.charAt(i) - '0';
        i++;
      }

      result.append(String.valueOf(s.charAt(i)).repeat(count));
    }

    return result.toString();
  }

  public static String encoding(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      int count = 1;
      while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
        count++;
        i++;
      }

      sb.append(count);
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }

  @EpiTest(testDataFile = "run_length_compression.tsv")
  public static void rleTester(String encoded, String decoded)
      throws TestFailure {
    if (!decoding(encoded).equals(decoded)) {
      throw new TestFailure("Decoding failed");
    }
    if (!encoding(decoded).equals(encoded)) {
      throw new TestFailure("Encoding failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RunLengthCompression.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
