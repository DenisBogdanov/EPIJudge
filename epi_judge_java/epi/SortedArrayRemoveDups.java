package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.List;
import java.util.Objects;

public class SortedArrayRemoveDups {
  // returns the number of valid entries after deletion.
  public static int deleteDuplicates(List<Integer> list) {
    if (list.isEmpty()) {
      return 0;
    }

    int currentIndex = 1;
    int invariantIndex = 0;

    while (currentIndex < list.size()) {
      if (!Objects.equals(list.get(currentIndex), list.get(invariantIndex))) {
        list.set(++invariantIndex, list.get(currentIndex));
      }
      currentIndex++;
    }

    for (int i = invariantIndex + 1; i < list.size(); i++) {
      list.set(i, 0);
    }


    return invariantIndex + 1;
  }

  @EpiTest(testDataFile = "sorted_array_remove_dups.tsv")
  public static List<Integer> deleteDuplicatesWrapper(TimedExecutor executor,
                                                      List<Integer> A)
      throws Exception {
    int end = executor.run(() -> deleteDuplicates(A));
    return A.subList(0, end);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArrayRemoveDups.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
