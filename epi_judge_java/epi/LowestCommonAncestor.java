package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashMap;
import java.util.Map;

public class LowestCommonAncestor {
    private static BinaryTreeNode<Integer> parent;

    public static BinaryTreeNode<Integer> lca(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {

        if (node0 == node1) return node0;

        Map<BinaryTreeNode<Integer>, Integer> nodeToAmountMap = new HashMap<>();
        dfs(tree, node0, node1, nodeToAmountMap);
        return parent;
    }

    private static int dfs(BinaryTreeNode<Integer> tree, BinaryTreeNode<Integer> node0, BinaryTreeNode<Integer> node1, Map<BinaryTreeNode<Integer>, Integer> nodeToAmountMap) {
        if (tree == null) return 0;

        Integer amount = nodeToAmountMap.get(tree);
        if (amount != null) return amount;

        int result = 0;
        if (tree == node0 || tree == node1) result++;

        int left = dfs(tree.left, node0, node1, nodeToAmountMap);
        int right = dfs(tree.right, node0, node1, nodeToAmountMap);

        result += left + right;

        if (result == 2 && left <= 1 && right <= 1) parent = tree;

        nodeToAmountMap.put(tree, result);
        return result;
    }

    @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TimedExecutor executor,
                                 BinaryTreeNode<Integer> tree, Integer key0,
                                 Integer key1) throws Exception {
        BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
        BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

        BinaryTreeNode<Integer> result =
                executor.run(() -> lca(tree, node0, node1));

        if (result == null) {
            throw new TestFailure("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
