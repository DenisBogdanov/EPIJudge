package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {

    @EpiTest(testDataFile = "convert_base.tsv")
    public static String convertBase(String numAsString, int b1, int b2) {
        if ("0".equals(numAsString)) return "0";
        boolean isNegative = numAsString.startsWith("-");
        long decimal = convertToDecimal(isNegative ? numAsString.substring(1) : numAsString, b1);
        return (isNegative ? "-" : "") + convertFromDecimal(decimal, b2);
    }

    private static String convertFromDecimal(long decimal, int base) {
        StringBuilder sb = new StringBuilder();
        while (decimal > 0) {
            long remainder = decimal % base;
            decimal /= base;
            if (remainder < 10) {
                sb.append(remainder);
            } else {
                sb.append((char) ('A' + remainder - 10));
            }
        }

        sb.reverse();
        return sb.toString();
    }

    private static long convertToDecimal(String numAsString, int base) {
        long result = 0;
        for (int i = 0; i < numAsString.length(); i++) {
            result *= base;
            char c = numAsString.charAt(i);
            if (Character.isDigit(c)) {
                result += c - '0';
            } else {
                result += 10 + c - 'A';
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
