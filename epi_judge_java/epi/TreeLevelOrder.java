package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeLevelOrder {

    @EpiTest(testDataFile = "tree_level_order.tsv")
    public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
        List<List<Integer>> result = new ArrayList<>();
        if (tree == null) return result;

        Queue<BinaryTreeNode<Integer>> q = new ArrayDeque<>();
        q.offer(tree);

        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = q.size();

            for (int i = 0; i < size; i++) {
                BinaryTreeNode<Integer> polled = q.poll();
                level.add(polled.data);

                if (polled.left != null) q.offer(polled.left);
                if (polled.right != null) q.offer(polled.right);
            }

            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
