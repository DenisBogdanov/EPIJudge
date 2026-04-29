package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.*;

public class Anagrams {

    @EpiTest(testDataFile = "anagrams.tsv")
    public static List<List<String>> findAnagrams(List<String> dictionary) {
        Map<String, List<String>> sortedToAnagramsMap = new HashMap<>();
        for (String s : dictionary) {
            var chars = s.toCharArray();
            Arrays.sort(chars);
            var sortedStr = new String(chars);
            sortedToAnagramsMap.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(s);
        }
        List<List<String>> ans = new ArrayList<>();
        for (var entry : sortedToAnagramsMap.entrySet()) {
            if (entry.getValue().size() == 1) continue;
            ans.add(entry.getValue());
        }
        return ans;
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
