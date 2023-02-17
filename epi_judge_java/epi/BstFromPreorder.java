package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder {

    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        if (preorderSequence.isEmpty()) return null;
        BstNode<Integer> root = new BstNode<>(preorderSequence.get(0));
        int pivotIndex = 1;
        while (pivotIndex < preorderSequence.size() && preorderSequence.get(pivotIndex) < preorderSequence.get(0)) {
            pivotIndex++;
        }

        root.left = rebuildBSTFromPreorder(preorderSequence.subList(1, pivotIndex));
        root.right = rebuildBSTFromPreorder(preorderSequence.subList(pivotIndex, preorderSequence.size()));

        return root;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
