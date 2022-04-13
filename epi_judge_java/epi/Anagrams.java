package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

  @EpiTest(testDataFile = "anagrams.tsv")
  public static List<List<String>> findAnagrams(List<String> dictionary) {
    Map<String, List<String>> sortedStringToAnagramsMap = new HashMap<>();
    for (String str : dictionary) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String sortedStr = new String(chars);

      sortedStringToAnagramsMap.computeIfAbsent(sortedStr, k -> new ArrayList<>())
          .add(str);
    }

    return sortedStringToAnagramsMap.values().stream()
        .filter(list -> list.size() >= 2)
        .collect(Collectors.toList());
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
