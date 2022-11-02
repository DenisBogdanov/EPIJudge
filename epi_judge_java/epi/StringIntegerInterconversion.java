package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        if (x == 0) return "0";
        if (x == Integer.MIN_VALUE) return "-2147483648";

        boolean isNegative = x < 0;
        x = Math.abs(x);

        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            sb.append(x % 10);
            x /= 10;
        }

        return (isNegative ? '-' : "") + sb.reverse().toString();
    }

    public static int stringToInt(String s) {
        int index = 0;
        int sign = 1;
        if (s.charAt(index) == '-') {
            index++;
            sign = -1;
        } else if (s.charAt(index) == '+') {
            index++;
        }

        int result = 0;
        while (index < s.length()) {
            result *= 10;
            result += (s.charAt(index) - '0');
            index++;
        }

        return sign * result;
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
        System.exit(GenericTest.runFromAnnotations(args, "StringIntegerInterconversion.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }
}
