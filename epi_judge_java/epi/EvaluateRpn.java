package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.LinkedList;

/**
 * ToDo: validation is skipped (expecting only valid expressions)
 */
public class EvaluateRpn {

    @EpiTest(testDataFile = "evaluate_rpn.tsv")
    public static int eval(String expression) {
        var elements = expression.split(",");
        var numsStack = new LinkedList<Integer>();
        for (String element : elements) {
            switch (element) {
                case "+" -> {
                    int b = numsStack.poll();
                    int a = numsStack.poll();
                    numsStack.push(a + b);
                }
                case "-" -> {
                    int b = numsStack.poll();
                    int a = numsStack.poll();
                    numsStack.push(a - b);
                }
                case "/" -> {
                    int b = numsStack.poll();
                    int a = numsStack.poll();
                    numsStack.push(a / b);
                }
                case "*" -> {
                    int b = numsStack.poll();
                    int a = numsStack.poll();
                    numsStack.push(a * b);
                }
                default -> {
                    numsStack.push(Integer.parseInt(element));
                }
            }
        }
        return numsStack.poll();
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
