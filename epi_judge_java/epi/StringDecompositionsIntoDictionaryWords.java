package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringDecompositionsIntoDictionaryWords {

  @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
  public static List<Integer> findAllSubstrings(String s, List<String> words) {
    Map<String, Integer> wordToFreq = new HashMap<>();

    for (String word : words) {
      wordToFreq.merge(word, 1, Integer::sum);
    }

    int wordSize = words.get(0).length();

    List<Integer> result = new ArrayList<>();

    for (int i = 0; i + wordSize * words.size() <= s.length(); ++i) {
      if (matchAHWordsInDict(s, wordToFreq, i, words.size(), wordSize)) {
        result.add(i);
      }
    }

    return result;
  }

  private static boolean matchAHWordsInDict(String s, Map<String, Integer> wordToFreq,
                                            int start, int numWords, int wordSize) {

    Map<String, Integer> currStringToFreq = new HashMap<>();

    for (int i = 0; i < numWords; ++i) {
      String currWord = s.substring(start + i * wordSize, start + (i + 1) * wordSize);

      Integer freq = wordToFreq.get(currWord);
      if (freq == null) {
        return false;
      }

      currStringToFreq.merge(currWord, 1, Integer::sum);

      if (currStringToFreq.get(currWord) > freq) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.exit(GenericTest
        .runFromAnnotations(
            args, "StringDecompositionsIntoDictionaryWords.java",
            new Object() {
            }.getClass().getEnclosingClass())
        .ordinal());
  }
}
