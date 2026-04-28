package epi;

import epi.test_framework.*;

public class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> lca(BinaryTree<Integer> n1, BinaryTree<Integer> n2) {
        int d1 = 0;
        var runner = n1;
        while (runner != null) {
            d1++;
            runner = runner.parent;
        }
        int d2 = 0;
        runner = n2;
        while (runner != null) {
            d2++;
            runner = runner.parent;
        }
        while (d1 > d2) {
            d1--;
            n1 = n1.parent;
        }
        while (d2 > d1) {
            d2--;
            n2 = n2.parent;
        }
        while (n1 != n2) {
            n1 = n1.parent;
            n2 = n2.parent;
        }
        return n1;
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
