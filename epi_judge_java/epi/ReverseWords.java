package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class ReverseWords {

  public static void reverseWords(char[] input) {
    reverse(input, 0, input.length);

    int start = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i] == ' ') {
        reverse(input, start, i);
        start = i + 1;
      }
    }

    reverse(input, start, input.length);
  }

  private static void reverse(char[] array, int from, int to) {
    int mid = from + (to - from) / 2;
    for (int i = from; i < mid; i++) {
      char temp = array[i];
      array[i] = array[to + from - 1 - i];
      array[to + from - 1 - i] = temp;
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
