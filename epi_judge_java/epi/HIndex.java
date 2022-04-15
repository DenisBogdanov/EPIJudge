package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class HIndex {

  @EpiTest(testDataFile = "h_index.tsv")
  public static int hIndex(List<Integer> citations) {
    Collections.sort(citations);

    int size = citations.size();
    for (int i = 0; i < size; i++) {
      if (citations.get(i) >= size - i) {
        return size - i;
      }
    }

    return 0;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "HIndex.java",
                new Object() {
                }.getClass().getEnclosingClass())
            .ordinal());
  }
}
