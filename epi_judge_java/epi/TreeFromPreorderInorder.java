package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeFromPreorderInorder {

    @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
    public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
        Map<Integer, Integer> inorderToIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            inorderToIndexMap.put(inorder.get(i), i);
        }

        return build(preorder, 0, preorder.size(), inorderToIndexMap, 0, inorder.size());
    }

    private static BinaryTreeNode<Integer> build(List<Integer> preorder, int pLeft, int pRight, Map<Integer, Integer> inorderToIndexMap, int iLeft, int iRight) {
        if (pLeft >= pRight || iLeft >= iRight) return null;

        int rootInorderIndex = inorderToIndexMap.get(preorder.get(pLeft));
        int leftSubtreeSize = rootInorderIndex - iLeft;

        return new BinaryTreeNode<>(preorder.get(pLeft),
                build(preorder, pLeft + 1, pLeft + 1 + leftSubtreeSize, inorderToIndexMap, iLeft, rootInorderIndex),
                build(preorder, pLeft + 1 + leftSubtreeSize, pRight, inorderToIndexMap, rootInorderIndex + 1, iRight));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeFromPreorderInorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
