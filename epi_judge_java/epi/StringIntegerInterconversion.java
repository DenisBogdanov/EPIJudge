package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;

public class StringIntegerInterconversion {

    public static String intToString(int x) {
        if (x == 0) return "0";
        long num = x;
        boolean isNegative = num < 0;
        if (isNegative) num = -num;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 10);
            num /= 10;
        }
        if (isNegative) sb.append('-');
        return sb.reverse().toString();
    }

    public static int stringToInt(String s) {
        boolean isNegative = false;
        int start = 0;
        if (s.charAt(start) == '-') {
            start++;
            isNegative = true;
        } else if (s.charAt(start) == '+') {
            start++;
        }

        long ans = 0;
        for (int i = start; i < s.length(); i++) {
            ans *= 10;
            ans += s.charAt(i) - '0';
        }
        if (isNegative) ans *= -1;
        return (int) ans;
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
