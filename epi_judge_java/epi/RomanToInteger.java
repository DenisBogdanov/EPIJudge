package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Map;

public class RomanToInteger {

    @EpiTest(testDataFile = "roman_to_integer.tsv")
    public static int romanToInteger(String s) {
        Map<Character, Integer> mapping = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1_000
        );

        int result = 0;
        int currMax = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int num = mapping.get(s.charAt(i));
            currMax = Math.max(currMax, num);
            if (num < currMax) {
                result -= num;
            } else {
                result += num;
            }
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
