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
        List<String> ans = new ArrayList<>();
        recur(0, numPairs * 2, 0, new StringBuilder(), ans);
        return ans;
    }

    private static void recur(int i, int total, int balance, StringBuilder sb, List<String> ans) {
        if (i == total) {
            if (balance == 0) {
                ans.add(sb.toString());
            }
            return;
        }
        sb.append('(');
        recur(i + 1, total, balance + 1, sb, ans);
        sb.setLength(sb.length() - 1);
        if (balance > 0) {
            sb.append(')');
            recur(i + 1, total, balance - 1, sb, ans);
            sb.setLength(sb.length() - 1);
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
