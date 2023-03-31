package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorCloseAncestor {

    public static BinaryTree<Integer> lca(BinaryTree<Integer> node0, BinaryTree<Integer> node1) {
        if (node0 == node1) return node0;

        var parent0 = node0;
        var parent1 = node1;

        Set<BinaryTree<Integer>> parents0 = new HashSet<>();
        parents0.add(parent0);

        Set<BinaryTree<Integer>> parents1 = new HashSet<>();
        parents1.add(parent1);

        while (parent0 != null || parent1 != null) {
            if (parent0 != null) {
                parent0 = parent0.parent;
                parents0.add(parent0);
            }

            if (parent1 != null) {
                parent1 = parent1.parent;
                parents1.add(parent1);
            }

            if (parents0.contains(parent1)) return parent1;
            if (parents1.contains(parent0)) return parent0;
        }

        return null;
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
                        .runFromAnnotations(args, "LowestCommonAncestorCloseAncestor.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
