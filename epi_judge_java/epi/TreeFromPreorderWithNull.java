package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeFromPreorderWithNull {
    private static final BinaryTreeNode<Integer> FAKE = new BinaryTreeNode<>(-1);

    public static BinaryTreeNode<Integer> reconstructPreorder(List<Integer> preorder) {
        if (preorder.isEmpty()) return null;
        if (preorder.get(0) == null) return null;

        Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(0));
        stack.push(root);

        for (int i = 1; i < preorder.size(); i++) {
            Integer next = preorder.get(i);
            if (next == null) {
                BinaryTreeNode<Integer> peeked = stack.peek();
                if (peeked.left == null) {
                    peeked.left = FAKE;
                } else if (peeked.right == null) {
                    stack.pop();

                    while (!stack.isEmpty() && stack.peek().left != null && stack.peek().right != null) {
                        stack.pop();
                    }
                }
            } else {
                BinaryTreeNode<Integer> nextNode = new BinaryTreeNode<>(next);
                BinaryTreeNode<Integer> peeked = stack.peek();
                if (peeked.left == null) {
                    peeked.left = nextNode;
                } else if (peeked.right == null) {
                    peeked.right = nextNode;
                }

                stack.push(nextNode);
            }
        }

        cleanIndorder(root);

        return root;
    }

    private static void cleanIndorder(BinaryTreeNode<Integer> root) {
        if (root != null) {
            if (root.left == FAKE) {
                root.left = null;
            } else {
                cleanIndorder(root.left);
            }

            cleanIndorder(root.right);
        }
    }

    @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
    public static BinaryTreeNode<Integer> reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
            throws Exception {
        List<Integer> ints = new ArrayList<>();
        for (String s : strings) {
            if (s.equals("null")) {
                ints.add(null);
            } else {
                ints.add(Integer.parseInt(s));
            }
        }

        return executor.run(() -> reconstructPreorder(ints));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
