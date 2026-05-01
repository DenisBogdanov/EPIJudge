package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvenOddListMerge {

    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    public static ListNode<Integer> evenOddMerge(ListNode<Integer> head) {
        int i = 0;
        var runner = head;
        var leftDummy = new ListNode<>(-1, null);
        var leftRunner = leftDummy;
        var rightDummy = new ListNode<>(-1, null);
        var rightRunner = rightDummy;
        while (runner != null) {
            if (i % 2 == 0) {
                leftRunner.next = new ListNode<>(runner.data, null);
                leftRunner = leftRunner.next;
            } else {
                rightRunner.next = new ListNode<>(runner.data, null);
                rightRunner = rightRunner.next;
            }
            i++;
            runner = runner.next;
        }
        leftRunner.next = rightDummy.next;
        return leftDummy.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "EvenOddListMerge.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
