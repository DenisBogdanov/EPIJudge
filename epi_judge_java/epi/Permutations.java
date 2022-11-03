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
    public static List<List<Integer>> permutations(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    private static void backtrack(List<Integer> nums, int index, List<List<Integer>> result) {
        if (index == nums.size() - 1) {
            result.add(new ArrayList<>(nums));
        } else {
            for (int i = index; i < nums.size(); i++) {
                Collections.swap(nums, i, index);
                backtrack(nums, index + 1, result);
                Collections.swap(nums, i, index);
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
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Permutations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
