package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

    public static String decoding(String s) {
        if (s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num *= 10;
                num += Character.toLowerCase(c) - '0';
            } else {
                sb.append(String.valueOf(c).repeat(num));
                num = 0;
            }
        }

        return sb.toString();
    }

    public static String encoding(String s) {
        if (s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        char prev = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == prev) {
                count++;
            } else {
                sb.append(count).append(prev);
                prev = c;
                count = 1;
            }
        }

        sb.append(count).append(prev);

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
