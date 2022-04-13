package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {

  @EpiTest(testDataFile = "evaluate_rpn.tsv")
  public static int eval(String expression) {
    String[] exprArr = expression.split(",");
    Deque<Integer> stack = new LinkedList<>();

    for (String s : exprArr) {
      switch (s) {
        case "+":
          stack.push((stack.pop() + stack.pop()));
          break;
        case "-":
          Integer subtrahend = stack.pop();
          stack.push(stack.pop() - subtrahend);
          break;
        case "*":
          stack.push(stack.pop() * stack.pop());
          break;
        case "/":
          Integer divider = stack.pop();
          stack.push(stack.pop() / divider);
          break;
        default:
          stack.push(Integer.parseInt(s));
          break;
      }
    }

    return stack.pop();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
