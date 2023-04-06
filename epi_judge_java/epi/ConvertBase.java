package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class ConvertBase {

    @EpiTest(testDataFile = "convert_base.tsv")
    public static String convertBase(String numAsString, int b1, int b2) {
        if (numAsString.equals("0")) return "0";
        boolean isNegative = false;
        int index = 0;
        if (numAsString.charAt(0) == '-') {
            isNegative = true;
            index = 1;
        }

        int decimal = 0;
        int mult = 1;
        for (int i = numAsString.length() - 1; i >= index; i--) {
            char c = numAsString.charAt(i);
            if (Character.isDigit(c)) {
                decimal += mult * (c - '0');
            } else {
                decimal += mult * (10 + c - 'A');
            }
            mult *= b1;
        }

        StringBuilder sb = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % b2;

            if (remainder <= 9) {
                sb.append(remainder);
            } else {
                sb.append((char) ('A' + (remainder - 10)));
            }

            decimal /= b2;
        }

        return (isNegative ? "-" : "") + sb.reverse();
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
