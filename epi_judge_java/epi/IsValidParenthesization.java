package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class IsValidParenthesization {

    @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
    public static boolean isWellFormed(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '}':
                    if (stack.isEmpty()) return false;
                    if (stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty()) return false;
                    if (stack.pop() != '[') return false;
                    break;
                case ')':
                    if (stack.isEmpty()) return false;
                    if (stack.pop() != '(') return false;
                    break;
                default:
                    stack.push(c);
                    break;
            }
        }

        return stack.isEmpty();
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
