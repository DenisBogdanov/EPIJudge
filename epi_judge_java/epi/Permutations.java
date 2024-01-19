package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {

    @EpiTest(testDataFile = "permutations.tsv")
    public static List<List<Integer>> permutations(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();

        recur(0, list, result);

        return result;
    }

    private static void recur(int index, List<Integer> list, List<List<Integer>> result) {
        if (index == list.size() - 1) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < list.size(); i++) {
            Collections.swap(list, i, index);
            recur(index + 1, list, result);
            Collections.swap(list, i, index);
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
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Permutations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
