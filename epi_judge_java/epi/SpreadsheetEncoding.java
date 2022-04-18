package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SpreadsheetEncoding {

  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")
  public static int ssDecodeColID(final String col) {
    int result = 0;

    for (int i = 0; i < col.length(); i++) {
      result *= 26;
      result += (col.charAt(i) - 'A' + 1);
    }

    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
