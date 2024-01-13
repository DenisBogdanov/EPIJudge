package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateRpn {

    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    public static int eval(String expression) {
        String[] symbols = expression.split(",");

        Deque<Integer> stack = new ArrayDeque<>();

        for (String symbol : symbols) {
            switch (symbol) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    Integer a = stack.pop();
                    stack.push(stack.pop() - a);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    Integer b = stack.pop();
                    stack.push(stack.pop() / b);
                    break;
                default:
                    stack.push(Integer.parseInt(symbol));
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
