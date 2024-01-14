package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {

    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        if (tree == null) return List.of();
        while (tree.left != null) {
            tree = tree.left;
        }

        List<Integer> result = new ArrayList<>();
        result.add(tree.data);
        BinaryTree<Integer> successor = findSuccessor(tree);
        while (successor != null) {
            result.add(successor.data);
            successor = findSuccessor(successor);
        }

        return result;
    }

    public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        if (node.right == null) {
            while (node.parent != null) {
                if (node == node.parent.left) return node.parent;
                node = node.parent;
            }
        } else {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        return null;
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
