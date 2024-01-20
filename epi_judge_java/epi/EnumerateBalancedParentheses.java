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

        recur("", numPairs, numPairs, result);

        return result;
    }

    private static void recur(String prefix, int remainingOpened, int remainingClosed, List<String> result) {
        if (remainingClosed == 0) {
            result.add(prefix);
        } else {
            if (remainingClosed > remainingOpened) {
                recur(prefix + ")", remainingOpened, remainingClosed - 1, result);
            }

            if (remainingOpened > 0) {
                recur(prefix + "(", remainingOpened - 1, remainingClosed, result);
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
