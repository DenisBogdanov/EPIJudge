package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        if (x == Integer.MIN_VALUE) return "-2147483648";
        if (x == 0) return "0";

        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = -x;
        }

        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            sb.append(x % 10);
            x /= 10;
        }

        return (isNegative ? "-" : "") + sb.reverse();
    }

    public static int stringToInt(String s) {
        boolean isNegative = false;
        int index = 0;

        if (s.charAt(0) == '-') {
            isNegative = true;
            index = 1;
        }

        if (s.charAt(0) == '+') {
            index = 1;
        }

        long result = 0;
        while (index < s.length()) {
            result *= 10;
            result += (s.charAt(index) - '0');
            index++;
        }

        return (int) ((isNegative ? -1 : 1) * result);
    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    public static void wrapper(int x, String s) throws TestFailure {
        if (Integer.parseInt(intToString(x)) != x) {
            throw new TestFailure("Int to string conversion failed");
        }
        if (stringToInt(s) != x) {
            throw new TestFailure("String to int conversion failed");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
