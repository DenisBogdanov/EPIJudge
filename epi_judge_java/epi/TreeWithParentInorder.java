package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {

    @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        if (tree == null) return List.of();
        List<Integer> ans = new ArrayList<>();
        while (tree.left != null) {
            tree = tree.left;
        }
        while (tree != null) {
            ans.add(tree.data);
            tree = SuccessorInTree.findSuccessor(tree);
        }
        return ans;
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
