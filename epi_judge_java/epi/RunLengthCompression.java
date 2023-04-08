package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class RunLengthCompression {

    public static String decoding(String s) {
        StringBuilder sb = new StringBuilder();

        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count *= 10;
                count += c - '0';
            } else {
                sb.append((c + "").repeat(count));
                count = 0;
            }
        }

        return sb.toString();
    }

    public static String encoding(String s) {
        StringBuilder sb = new StringBuilder();
        char prev = s.charAt(0);
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == prev) {
                count++;
            } else {
                sb.append(count).append(prev);
                count = 1;
                prev = s.charAt(i);
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
