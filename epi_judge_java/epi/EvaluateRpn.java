package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateRpn {

    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    public static int eval(String expression) {
        String[] split = expression.split(",");
        Deque<Integer> stack = new ArrayDeque<>();

        for (String next : split) {
            switch (next) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "-":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                case "/":
                    int d = stack.pop();
                    int c = stack.pop();
                    stack.push(c / d);
                    break;
                default:
                    stack.push(Integer.parseInt(next));
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
