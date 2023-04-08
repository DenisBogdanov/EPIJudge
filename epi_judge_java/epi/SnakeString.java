package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SnakeString {

    @EpiTest(testDataFile = "snake_string.tsv")
    public static String snakeString(String s) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            sb1.append(i % 4 == 1 ? s.charAt(i) : "");
            sb2.append(i % 2 == 0 ? s.charAt(i) : "");
            sb3.append(i % 4 == 3 ? s.charAt(i) : "");
        }

        return sb1.append(sb2).append(sb3).toString();
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
