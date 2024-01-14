package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class SuccessorInTree {
    static int id = 0;

    public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        id++;
        if (node == null) return null;

        if (node.right == null) {
            while (node.parent != null) {
                if (node == node.parent.left) return node.parent;
                node = node.parent;
            }

            return null;
        } else {
            BinaryTree<Integer> runner = node.right;
            while (runner.left != null) {
                runner = runner.left;
            }

            return runner;
        }
    }

    @EpiTest(testDataFile = "successor_in_tree.tsv")
    public static int findSuccessorWrapper(TimedExecutor executor, BinaryTree<Integer> tree, int nodeIdx)
            throws Exception {
        BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

        BinaryTree<Integer> result = executor.run(() -> findSuccessor(n));

        return result == null ? -1 : result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SuccessorInTree.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
