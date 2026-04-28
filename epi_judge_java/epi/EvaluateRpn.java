package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateRpn {

    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    public static int eval(String expression) {
        var parts = expression.split(",");
        Deque<Integer> stack = new ArrayDeque<>();
        for (String part : parts) {
            switch (part) {
                case "+": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                    break;
                }
                case "*": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a * b);
                    break;
                }
                case "-": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                    break;
                }
                case "/": {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b / a);
                    break;
                }
                default: {
                    stack.push(Integer.parseInt(part));
                    break;
                }
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
