package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> a, List<Integer> b) {
        int i = 0;
        int j = 0;
        List<Integer> result = new ArrayList<>();

        while (i < a.size() && j < b.size()) {
            while (i < a.size() && a.get(i) < b.get(j)) {
                i++;
            }

            if (i == a.size()) break;

            if (a.get(i).equals(b.get(j))) result.add(a.get(i));
            j++;

            while (j < b.size() && b.get(j - 1).equals(b.get(j))) {
                j++;
            }
        }

        return result;
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
