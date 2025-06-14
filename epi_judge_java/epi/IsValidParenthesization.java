package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Map;

public class IsValidParenthesization {
    private static final Map<Character, Character> CLOSED_TO_OPENED_BRACKETS_MAP = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );

    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    public static boolean isWellFormed(String s) {
        var bracketsStack = new ArrayList<Character>();
        for (char bracket : s.toCharArray()) {
            switch (bracket) {
                case ')', ']', '}' -> {
                    if (bracketsStack.isEmpty()) {
                        return false;
                    }
                    if (bracketsStack.removeLast() != CLOSED_TO_OPENED_BRACKETS_MAP.get(bracket)) {
                        return false;
                    }
                }
                default -> bracketsStack.add(bracket);
            }
        }

        return bracketsStack.isEmpty();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsValidParenthesization.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
