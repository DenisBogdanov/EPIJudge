package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

    @EpiTest(testDataFile = "three_sum.tsv")
    public static boolean hasThreeSum(List<Integer> list, int t) {
        Set<Integer> set = new HashSet<>(list);
        if (t % 3 == 0 && set.contains(t / 3)) return true;

        int n = list.size();
        for (int i = 0; i < n; i++) {
            int a = list.get(i);
            if (set.contains(t - 2 * a) || ((t - a) % 2 == 0 && set.contains((t - a) / 2))) {
                return true;
            }

            for (int j = i + 1; j < n; j++) {
                if (set.contains(t - a - list.get(j))) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
