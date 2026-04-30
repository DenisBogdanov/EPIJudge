package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

    public static void reverseWords(char[] input) {
        reverse(input, 0, input.length);
        int start = 0;
        while (start < input.length && input[start] == ' ') start++;
        for (int i = 1; i < input.length; i++) {
            if (input[i] != ' ' && input[i - 1] == ' '){
                start = i;
            }
            if ((input[i] == ' ' && input[i - 1] != ' ')) {
                reverse(input, start, i);
                start = -1;
            }
        }
        if (start != -1) reverse(input, start, input.length);
    }

    private static void reverse(char[] arr, int start, int end) {
        int left = start;
        int right = end - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
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
