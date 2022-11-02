package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return isBalancedDfs(tree).balanced;
    }

    private static Stat isBalancedDfs(BinaryTreeNode<Integer> node) {
        if (node == null) return new Stat(0, true);

        Stat left = isBalancedDfs(node.left);
        if (!left.balanced) return new Stat(0, false);

        Stat right = isBalancedDfs(node.right);
        if (!right.balanced) return new Stat(0, false);

        if (Math.abs(left.height - right.height) > 1) {
            return new Stat(0, false);
        }

        return new Stat(1 + Math.max(left.height, right.height), true);
    }

    static final class Stat {
        final int height;
        final boolean balanced;

        Stat(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    public static void main(String[] args) {
        System.exit(GenericTest.runFromAnnotations(args, "IsTreeBalanced.java", new Object() {
        }.getClass().getEnclosingClass()).ordinal());
    }
}
