package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class IsValidParenthesization {

  @EpiTest(testDataFile = "is_valid_parenthesization.tsv")
  public static boolean isWellFormed(String s) {
    Deque<Character> stack = new LinkedList<>();

    for (char c : s.toCharArray()) {
      switch (c) {
        case '(':
        case '[':
        case '{':
          stack.push(c);
          break;
        case ')':
          if (stack.isEmpty() || stack.peek() != '(') {
            return false;
          }
          stack.pop();
          break;
        case ']':
          if (stack.isEmpty() || stack.peek() != '[') {
            return false;
          }
          stack.pop();
          break;
        case '}':
          if (stack.isEmpty() || stack.peek() != '{') {
            return false;
          }
          stack.pop();
          break;
        default:
          throw new IllegalArgumentException();
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
