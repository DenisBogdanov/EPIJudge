package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class RomanToInteger {

    @EpiTest(testDataFile = "roman_to_integer.tsv")
    public static int romanToInteger(String s) {
        int result = 0;
        for (char c : s.toCharArray()) {
            switch (c) {
                case 'I':
                    result += 1;
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    result += 10;
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    result += 100;
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1_000;
                    break;
            }
        }

        if (s.contains("IV") || s.contains("IX")) {
            result -= 2;
        }

        if (s.contains("XL") || s.contains("XC")) {
            result -= 20;
        }

        if (s.contains("CD") || s.contains("CM")) {
            result -= 200;
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RomanToInteger.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
