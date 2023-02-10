package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                          BinaryTree<Integer> node1) {

        int depth0 = 0;
        var runner = node0;
        while (runner != null) {
            depth0++;
            runner = runner.parent;
        }

        int depth1 = 0;
        runner = node1;
        while (runner != null) {
            depth1++;
            runner = runner.parent;
        }

        while (depth1 > depth0) {
            depth1--;
            node1 = node1.parent;
        }

        while (depth0 > depth1) {
            depth0--;
            node0 = node0.parent;
        }

        while (node0 != node1) {
            node0 = node0.parent;
            node1 = node1.parent;
        }

        return node0;
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                                 Integer key0, Integer key1) throws Exception {
        BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BinaryTree<Integer> result = executor.run(() -> lca(node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
