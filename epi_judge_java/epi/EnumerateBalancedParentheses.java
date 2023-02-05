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
        recur(numPairs, 0, 0, new StringBuilder(), result);
        return result;
    }

    private static void recur(int numPairs, int openedCount, int closedCount, StringBuilder sb, List<String> result) {
        if (openedCount == numPairs && closedCount == numPairs) {
            result.add(sb.toString());
        } else {
            if (openedCount < numPairs) {
                sb.append("(");
                recur(numPairs, openedCount + 1, closedCount, sb, result);
                sb.setLength(sb.length() - 1);
            }

            if (openedCount > closedCount) {
                sb.append(")");
                recur(numPairs, openedCount, closedCount + 1, sb, result);
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
