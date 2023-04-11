package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NextPermutation {

    @EpiTest(testDataFile = "next_permutation.tsv")
    public static List<Integer> nextPermutation(List<Integer> perm) {
        int n = perm.size();
        int greaterIndex = n - 1;
        while (greaterIndex > 0 && perm.get(greaterIndex) <= perm.get(greaterIndex - 1)) {
            greaterIndex--;
        }

        if (greaterIndex == 0) return List.of();

        int lessIndex = greaterIndex - 1;
        while (greaterIndex < n && perm.get(lessIndex) < perm.get(greaterIndex)) {
            greaterIndex++;
        }

        greaterIndex--;
        Collections.swap(perm, lessIndex, greaterIndex);

        greaterIndex = n - 1;

        while (lessIndex + 1 < greaterIndex) {
            Collections.swap(perm, lessIndex + 1, greaterIndex);
            lessIndex++;
            greaterIndex--;
        }

        return perm;
    }

    public static void main(String[] args) {
//        nextPermutation(new ArrayList<>(Arrays.asList(1, 4, 8, 7)));
        nextPermutation(new ArrayList<>(Arrays.asList(2, 3, 1)));
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
