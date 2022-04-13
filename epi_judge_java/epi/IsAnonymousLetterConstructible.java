package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {

  @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
  public static boolean isLetterConstructibleFromMagazine(String letterText, String magazineText) {
    Map<Character, Integer> charToCountMap = new HashMap<>();

    for (char c : letterText.toCharArray()) {
      charToCountMap.merge(c, 1, Integer::sum);
    }

    for (char c : magazineText.toCharArray()) {
      if (charToCountMap.containsKey(c)) {
        if (charToCountMap.get(c) == 1) {
          charToCountMap.remove(c);
        } else {
          charToCountMap.put(c, charToCountMap.get(c) - 1);
        }
      }

      if (charToCountMap.isEmpty()) {
        break;
      }
    }

    return charToCountMap.isEmpty();
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
