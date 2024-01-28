package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortIncreasingDecreasingArray {

    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> nums) {
        List<List<Integer>> lists = new ArrayList<>();

        int start = 0;
        boolean shouldIncrease = true;
        for (int i = 1; i < nums.size(); i++) {
            if (shouldIncrease && nums.get(i) < nums.get(i - 1)) {
                shouldIncrease = false;
                lists.add(nums.subList(start, i));
                start = i;
            } else if (!shouldIncrease && nums.get(i) > nums.get(i - 1)) {
                ArrayList<Integer> subList = new ArrayList<>(nums.subList(start, i));
                Collections.reverse(subList);
                lists.add(subList);
                start = i;
            }
        }

        if (shouldIncrease) {
            lists.add(nums.subList(start, nums.size()));
        } else {
            ArrayList<Integer> subList = new ArrayList<>(nums.subList(start, nums.size()));
            Collections.reverse(subList);
            lists.add(subList);
        }

        return SortedArraysMerge.mergeSortedArrays(lists);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
