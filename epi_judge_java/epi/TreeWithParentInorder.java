package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {

    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        List<Integer> result = new ArrayList<>();
        if (tree == null) return result;

        while (tree.left != null) {
            tree = tree.left;
        }

        result.add(tree.data);

        BinaryTree<Integer> successor = SuccessorInTree.findSuccessor(tree);
        while (successor != null) {
            result.add(successor.data);
            successor = SuccessorInTree.findSuccessor(successor);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
