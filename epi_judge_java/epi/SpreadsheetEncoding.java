package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SpreadsheetEncoding {

    @EpiTest(testDataFile = "spreadsheet_encoding.tsv")
    public static int ssDecodeColID(final String col) {
        int ans = 0;
        for (int i = 0; i < col.length(); i++) {
            ans *= 26;
            ans += col.charAt(i) - 'A' + 1;
        }
        return ans;
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
