package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringDecompositionsIntoDictionaryWords {

    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")
    public static List<Integer> findAllSubstrings(String s, List<String> words) {
        List<Integer> result = new ArrayList<>();

        int len = words.get(0).length();
        int n = words.size();
        int windowSize = n * len;

        Set<String> wordsSet = new HashSet<>(words);

        for (int shift = 0; shift < len; shift++) {
            if (windowSize + shift > s.length()) return result;
            Map<String, Integer> currWordToCountMap = new HashMap<>();
            for (String word : words) {
                currWordToCountMap.merge(word, 1, Integer::sum);
            }

            for (int i = shift; i + len <= windowSize + shift; i += len) {
                String nextWord = s.substring(i, i + len);
                if (wordsSet.contains(nextWord)) {
                    Integer count = currWordToCountMap.get(nextWord);
                    if (count == null) {
                        currWordToCountMap.put(nextWord, -1);
                    } else if (count == 1) {
                        currWordToCountMap.remove(nextWord);
                    } else {
                        currWordToCountMap.merge(nextWord, -1, Integer::sum);
                    }
                }
            }

            if (currWordToCountMap.isEmpty()) result.add(shift);

            for (int i = windowSize + shift; i + len <= s.length(); i += len) {
                String prevWord = s.substring(i - windowSize, i - windowSize + len);
                if (wordsSet.contains(prevWord)) {
                    currWordToCountMap.merge(prevWord, 1, Integer::sum);
                    if (currWordToCountMap.get(prevWord) == 0) {
                        currWordToCountMap.remove(prevWord);
                    }
                }

                String nextWord = s.substring(i, i + len);
                if (wordsSet.contains(nextWord)) {
                    Integer count = currWordToCountMap.get(nextWord);
                    if (count == null) {
                        currWordToCountMap.put(nextWord, -1);
                    } else if (count == 1) {
                        currWordToCountMap.remove(nextWord);
                    } else {
                        currWordToCountMap.merge(nextWord, -1, Integer::sum);
                    }
                }

                if (currWordToCountMap.isEmpty()) result.add(i - windowSize + len);
            }
        }

        Collections.sort(result);
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
