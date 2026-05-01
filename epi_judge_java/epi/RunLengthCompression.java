package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

    public static String decoding(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                count *= 10;
                count += s.charAt(i) - '0';
            } else {
                sb.append(String.valueOf(s.charAt(i)).repeat(Math.max(0, count)));
                count = 0;
            }
        }
        sb.append(String.valueOf(s.charAt(s.length() - 1)).repeat(Math.max(0, count)));
        return sb.toString();
    }

    public static String encoding(String s) {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        sb.append(count).append(s.charAt(s.length() - 1));
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
