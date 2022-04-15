package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int decreasingUntilIndex = perm.size() - 2;
    while (decreasingUntilIndex >= 0 &&
        perm.get(decreasingUntilIndex) >= perm.get(decreasingUntilIndex + 1)) {

      decreasingUntilIndex--;
    }

    if (decreasingUntilIndex == -1) {
      return List.of();
    }

    for (int i = perm.size() - 1; i > decreasingUntilIndex; i--) {
      if (perm.get(i) > perm.get(decreasingUntilIndex)) {
        Collections.swap(perm, i, decreasingUntilIndex);
        break;
      }
    }

    Collections.reverse(perm.subList(decreasingUntilIndex + 1, perm.size()));
    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
