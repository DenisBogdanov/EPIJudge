package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeInorder {

    @EpiTest(testDataFile = "tree_inorder.tsv")
    public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
        List<Integer> result = new ArrayList<>();

        Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
        var runner = tree;

        while (!stack.isEmpty() || runner != null) {
            if (runner != null) {
                stack.push(runner);
                runner = runner.left;
            } else {
                runner = stack.pop();
                result.add(runner.data);
                runner = runner.right;
            }
        }

        return result;
    }


    private static class NodeAndState {
        public BinaryTreeNode<Integer> node;
        public Boolean leftSubtreeTraversed;

        public NodeAndState(BinaryTreeNode<Integer> node,
                            Boolean leftSubtreeTraversed) {
            this.node = node;
            this.leftSubtreeTraversed = leftSubtreeTraversed;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
