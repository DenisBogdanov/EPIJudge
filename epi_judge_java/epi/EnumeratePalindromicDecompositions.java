package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;

public class EnumeratePalindromicDecompositions {

    @EpiTest(testDataFile = "enumerate_palindromic_decompositions.tsv")
    public static List<List<String>> palindromeDecompositions(String text) {
        List<List<String>> result = new ArrayList<>();

        recur(0, text, new ArrayList<>(), result);
        return result;
    }

    private static void recur(int start, String text, List<String> curr, List<List<String>> result) {
        if (start == text.length()) {
            result.add(new ArrayList<>(curr));
            return;
        }

        for (int end = start; end < text.length(); end++) {
            if (isPalindrome(text, start, end)) {
                curr.add(text.substring(start, end + 1));
                recur(end + 1, text, curr, result);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String text, int start, int end) {
        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    @EpiTestComparator
    public static boolean comp(List<List<String>> expected,
                               List<List<String>> result) {
        if (result == null) {
            return false;
        }
        expected.sort(new LexicographicalListComparator<>());
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EnumeratePalindromicDecompositions.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
