package epi;

import epi.test_framework.*;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorCloseAncestor {

    public static BinaryTree<Integer> lca(BinaryTree<Integer> node1, BinaryTree<Integer> node2) {
        Set<BinaryTree<Integer>> seen = new HashSet<>();
        while (node1 != null || node2 != null) {
            if (node1 != null) {
                if (seen.contains(node1)) return node1;
                seen.add(node1);
                node1 = node1.parent;
            }
            if (node2 != null) {
                if (seen.contains(node2)) return node2;
                seen.add(node2);
                node2 = node2.parent;
            }
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
