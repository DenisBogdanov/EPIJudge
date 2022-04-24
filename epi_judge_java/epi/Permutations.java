package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {

  @EpiTest(testDataFile = "permutations.tsv")
  public static List<List<Integer>> permutations(List<Integer> list) {
    List<List<Integer>> result = new ArrayList<>();
    solve(list, 0, result);
    return result;
  }

  private static void solve(List<Integer> list, int index, List<List<Integer>> result) {
    if (index == list.size() - 1) {
      result.add(new ArrayList<>(list));
    } else {
      for (int j = index; j < list.size(); j++) {
        Collections.swap(list, index, j);
        solve(list, index + 1, result);
        Collections.swap(list, index, j);
      }
    }
  }

  @EpiTestComparator
  public static boolean comp(List<List<Integer>> expected,
                             List<List<Integer>> result) {
    if (result == null) {
      return false;
    }
    for (List<Integer> l : expected) {
      Collections.sort(l);
    }
    expected.sort(new LexicographicalListComparator<>());
    for (List<Integer> l : result) {
      Collections.sort(l);
    }
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  }

  public static void main(String[] args) {
    System.out.println(permutations(new ArrayList<>(List.of(1, 2, 3))));
//    System.exit(
//        GenericTest
//            .runFromAnnotations(args, "Permutations.java",
//                new Object() {
//                }.getClass().getEnclosingClass())
//            .ordinal());
  }
}
