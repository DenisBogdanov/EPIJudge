package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.Map;

public class IsTreeBalanced {
    private static boolean isBalanced = true;

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        isBalanced = true;
        Map<BinaryTreeNode<Integer>, Integer> nodeToHeightMap = new HashMap<>();
        findHeights(tree, nodeToHeightMap);
        return isBalanced;
    }

    private static int findHeights(BinaryTreeNode<Integer> tree, Map<BinaryTreeNode<Integer>, Integer> nodeToHeightMap) {
        if (tree == null) return 0;

        Integer height = nodeToHeightMap.get(tree);
        if (height != null) return height;

        int left = findHeights(tree.left, nodeToHeightMap);
        int right = findHeights(tree.right, nodeToHeightMap);

        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }

        height = Math.max(left, right) + 1;
        nodeToHeightMap.put(tree, height);
        return height;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
