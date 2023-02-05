package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumerateBalancedParentheses {

    @EpiTest(testDataFile = "enumerate_balanced_parentheses.tsv")
    public static List<String> generateBalancedParentheses(int numPairs) {
        List<String> result = new ArrayList<>();
        recur(numPairs, numPairs, new StringBuilder(), result);
        return result;
    }

    private static void recur(int openedCountNeeded, int closedCountNeeded, StringBuilder sb, List<String> result) {
        if (openedCountNeeded == 0 && closedCountNeeded == 0) {
            result.add(sb.toString());
        } else {
            if (openedCountNeeded > 0) {
                sb.append("(");
                recur(openedCountNeeded - 1, closedCountNeeded, sb, result);
                sb.setLength(sb.length() - 1);
            }

            if (openedCountNeeded < closedCountNeeded) {
                sb.append(")");
                recur(openedCountNeeded, closedCountNeeded - 1, sb, result);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    @EpiTestComparator
    public static boolean comp(List<String> expected, List<String> result) {
        if (result == null) {
            return false;
        }
        Collections.sort(expected);
        Collections.sort(result);
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EnumerateBalancedParentheses.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
