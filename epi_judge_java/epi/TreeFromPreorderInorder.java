package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TreeFromPreorderInorder {

    @EpiTest(testDataFile = "tree_from_preorder_inorder.tsv")
    public static BinaryTreeNode<Integer> binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
        if (preorder.size() == 0) return null;
        Integer rootValue = preorder.get(0);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootValue);
        int indexOfRootValue = inorder.indexOf(rootValue);
        root.left = binaryTreeFromPreorderInorder(preorder.subList(1, indexOfRootValue + 1),
                inorder.subList(0, indexOfRootValue));

        root.right = binaryTreeFromPreorderInorder(preorder.subList(indexOfRootValue + 1, preorder.size()),
                inorder.subList(indexOfRootValue + 1, inorder.size()));

        return root;
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
