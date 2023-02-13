package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class SuccessorInTree {

    public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        if (node.right != null) {
            BinaryTree<Integer> runner = node.right;
            while (runner.left != null) {
                runner = runner.left;
            }

            return runner;
        } else if (node.parent == null) {
            return null;
        } else if (node.parent.left == node) {
            return node.parent;
        } else {
            BinaryTree<Integer> runner = node.parent;
            BinaryTree<Integer> parent = runner.parent;

            while (parent != null && parent.right == runner) {
                parent = parent.parent;
                runner = runner.parent;
            }

            return parent;
        }
    }

    @EpiTest(testDataFile = "successor_in_tree.tsv")
    public static int findSuccessorWrapper(TimedExecutor executor,
                                           BinaryTree<Integer> tree, int nodeIdx) throws Exception {
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
