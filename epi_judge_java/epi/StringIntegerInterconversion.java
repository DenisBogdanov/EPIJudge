package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        if (x == 0) return "0";

        long num = x;
        boolean isNegative = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            sb.append(num % 10);
            num /= 10;
        }

        if (isNegative) sb.append("-");
        sb.reverse();

        return sb.toString();
    }

    public static int stringToInt(String s) {
        long result = 0;
        int start = 0;

        boolean isNegative = false;

        if (s.charAt(start) == '-') {
            isNegative = true;
            start++;
        } else if (s.charAt(start) == '+') {
            start++;
        }

        for (int i = start; i < s.length(); i++) {
            result *= 10;
            result += s.charAt(i) - '0';
        }

        if (isNegative) result *= -1;

        return (int) result;
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
