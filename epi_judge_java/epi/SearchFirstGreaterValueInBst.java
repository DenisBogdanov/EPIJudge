package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SearchFirstGreaterValueInBst {

    public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree, Integer k) {
        BstNode<Integer> ans = null;
        BstNode<Integer> curr = tree;
        while (curr != null) {
            if (curr.data > k) {
                ans = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return ans;
    }

    @EpiTest(testDataFile = "search_first_greater_value_in_bst.tsv")
    public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                   Integer k) {
        BstNode<Integer> result = findFirstGreaterThanK(tree, k);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFirstGreaterValueInBst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
