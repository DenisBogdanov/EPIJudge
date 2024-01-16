package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

    public static void reverseWords(char[] input) {
        int n = input.length;
        reverse(input, 0, n - 1);
        int prev = 0;
        while (prev < n && input[prev] == ' ') {
            prev++;
        }

        for (int i = prev + 1; i < n; i++) {
            if (input[i] == ' ') {
                reverse(input, prev, i - 1);
                prev = i + 1;
            }
        }

        reverse(input, prev, n - 1);
    }

    private static void reverse(char[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    private static void swap(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s)
            throws Exception {
        char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
