package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectSortedArrays {

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> a, List<Integer> b) {
        List<Integer> ans = new ArrayList<>();
        if (a.size() > b.size()) {
            var temp = a;
            a = b;
            b = temp;
        }
        for (int i = 0; i < a.size(); i++) {
            if (i > 0 && a.get(i).equals(a.get(i - 1))) continue;
            if (Collections.binarySearch(b, a.get(i)) >= 0) {
                ans.add(a.get(i));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IntersectSortedArrays.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
