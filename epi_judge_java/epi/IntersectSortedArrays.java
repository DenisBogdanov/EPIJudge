package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntersectSortedArrays {

    @EpiTest(testDataFile = "intersect_sorted_arrays.tsv")
    public static List<Integer> intersectTwoSortedArrays(List<Integer> a, List<Integer> b) {
        int i1 = 0;
        int i2 = 0;

        List<Integer> result = new ArrayList<>();
        while (i1 < a.size() && i2 < b.size()) {
            if (a.get(i1).equals(b.get(i2))) {
                if (result.isEmpty() || !result.get(result.size() - 1).equals(a.get(i1))) {
                    result.add(a.get(i1));
                }

                i1++;
                i2++;
            } else if (a.get(i1) < b.get(i2)) {
                i1++;
            } else {
                i2++;
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
