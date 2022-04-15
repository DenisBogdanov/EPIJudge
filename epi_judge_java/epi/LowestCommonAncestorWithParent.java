package epi;

import epi.test_framework.*;

public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> lca(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {

    int depth0 = depth(node0);
    int depth1 = depth(node1);

    while (depth0 > depth1) {
      depth0--;
      node0 = node0.parent;
    }

    while (depth1 > depth0) {
      depth1--;
      node1 = node1.parent;
    }

    while (node0 != node1) {
      node1 = node1.parent;
      node0 = node0.parent;
    }

    return node0;
  }

  private static int depth(BinaryTree<Integer> node) {
    int result = 0;
    while (node.parent != null) {
      result++;
      node = node.parent;
    }
    return result;
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
