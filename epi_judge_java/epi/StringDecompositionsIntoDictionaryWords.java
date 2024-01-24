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
        Map<String, Integer> wordToCountMap = new HashMap<>();
        for (String word : words) {
            wordToCountMap.merge(word, 1, Integer::sum);
        }

        List<Integer> result = new ArrayList<>();

        int wordSize = words.get(0).length();

        for (int mod = 0; mod < wordSize; mod++) {
            int start = mod;
            Map<String, Integer> slidingWindow = new HashMap<>();

            for (int i = mod; i + wordSize <= s.length(); i += wordSize) {
                String word = s.substring(i, i + wordSize);
                Integer count = wordToCountMap.get(word);
                if (count != null) {
                    Integer newCount = slidingWindow.merge(word, 1, Integer::sum);
                    if (newCount > count) {
                        while (true) {
                            String wordToRemove = s.substring(start, start + wordSize);
                            slidingWindow.merge(wordToRemove, -1, Integer::sum);
                            start += wordSize;
                            if (wordToRemove.equals(word)) {
                                break;
                            }
                        }
                    }

                    if ((i - start + wordSize) / wordSize == words.size()) {
                        result.add(start);
                    }
                } else {
                    start = i + wordSize;
                    slidingWindow.clear();
                }
            }
        }

        result.sort(null);
        return result;
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
