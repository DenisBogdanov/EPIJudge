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

        recur(text, 0, new ArrayList<>(), result);

        return result;
    }

    private static void recur(String text, int start, List<String> currDecomposition, List<List<String>> result) {
        if (start == text.length()) {
            result.add(new ArrayList<>(currDecomposition));
        } else {
            for (int end = start + 1; end <= text.length(); end++) {
                if (isPalindrome(text, start, end)) {
                    currDecomposition.add(text.substring(start, end));
                    recur(text, end, currDecomposition, result);
                    currDecomposition.remove(currDecomposition.size() - 1);
                }
            }
        }
    }

    private static boolean isPalindrome(String text, int left, int right) {
        right--;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
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
