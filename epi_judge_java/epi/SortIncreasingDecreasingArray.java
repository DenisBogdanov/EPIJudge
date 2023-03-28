package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortIncreasingDecreasingArray {

    @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")
    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> list) {
        List<List<Integer>> lists = new ArrayList<>();

        int prev = Integer.MIN_VALUE;
        List<Integer> currList = new ArrayList<>();
        boolean isIncreasing = true;

        for (Integer num : list) {
            if (isIncreasing && num < prev) {
                lists.add(currList);
                isIncreasing = false;
                currList = new ArrayList<>();
            } else if (!isIncreasing && num > prev) {
                Collections.reverse(currList);
                lists.add(currList);
                isIncreasing = true;
                currList = new ArrayList<>();
            }

            prev = num;
            currList.add(num);
        }

        if (!isIncreasing) {
            Collections.reverse(currList);
        }

        lists.add(currList);

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
