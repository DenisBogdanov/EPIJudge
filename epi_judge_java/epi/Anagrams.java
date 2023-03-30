package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Anagrams {

    @EpiTest(testDataFile = "anagrams.tsv")
    public static List<List<String>> findAnagrams(List<String> dictionary) {
        Map<String, List<String>> sortedWordToWordsMap = new HashMap<>();

        for (String word : dictionary) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            sortedWordToWordsMap.computeIfAbsent(new String(letters), k -> new ArrayList<>())
                    .add(word);
        }

        List<List<String>> result = new ArrayList<>();
        for (List<String> words : sortedWordToWordsMap.values()) {
            if (words.size() > 1) {
                result.add(words);
            }
        }

        return result;
    }

    @EpiTestComparator
    public static boolean comp(List<List<String>> expected,
                               List<List<String>> result) {
        if (result == null) {
            return false;
        }
        for (List<String> l : expected) {
            Collections.sort(l);
        }
        expected.sort(new LexicographicalListComparator<>());
        for (List<String> l : result) {
            Collections.sort(l);
        }
        result.sort(new LexicographicalListComparator<>());
        return expected.equals(result);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Anagrams.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
