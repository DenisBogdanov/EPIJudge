package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsValidParenthesization {

    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    public static boolean isWellFormed(String s) {
        Deque<Character> brackets = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case ')':
                    if (brackets.isEmpty() || brackets.pop() != '(') return false;
                    break;
                case ']':
                    if (brackets.isEmpty() || brackets.pop() != '[') return false;
                    break;
                case '}':
                    if (brackets.isEmpty() || brackets.pop() != '{') return false;
                    break;
                default:
                    brackets.push(c);
                    break;
            }
        }

        return brackets.isEmpty();
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "IsValidParenthesization.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }
}
