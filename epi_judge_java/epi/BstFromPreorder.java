package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class BstFromPreorder {

    @EpiTest(testDataFile = "bst_from_preorder.tsv")
    public static BstNode<Integer> rebuildBSTFromPreorder(List<Integer> preorderSequence) {
        return build(preorderSequence, 0, preorderSequence.size());
    }

    private static BstNode<Integer> build(List<Integer> preorderSequence, int left, int right) {
        if (left >= right) return null;

        int transitionPoint = left + 1;
        while (transitionPoint < right && preorderSequence.get(transitionPoint) < preorderSequence.get(left)) {
            transitionPoint++;
        }

        return new BstNode<>(preorderSequence.get(left),
                build(preorderSequence, left + 1, transitionPoint),
                build(preorderSequence, transitionPoint, right));
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
