package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SnakeString {

    @EpiTest(testDataFile = "snake_string.tsv")
    public static String snakeString(String s) {
        StringBuilder[] result = new StringBuilder[3];
        for (int i = 0; i < 3; i++) {
            result[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); i++) {
            if (i % 4 == 1) {
                result[0].append(s.charAt(i));
            } else if (i % 4 == 3) {
                result[2].append(s.charAt(i));
            } else {
                result[1].append(s.charAt(i));
            }
        }

        return result[0].append(result[1]).append(result[2]).toString();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SnakeString.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
