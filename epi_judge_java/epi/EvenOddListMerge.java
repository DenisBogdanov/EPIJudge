package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvenOddListMerge {

    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    public static ListNode<Integer> evenOddMerge(ListNode<Integer> head) {
        var evenDummy = new ListNode<>(-1, null);
        var oddDummy = new ListNode<>(-1, null);

        var evenRunner = evenDummy;
        var oddRunner = oddDummy;

        var runner = head;
        while (runner != null) {
            oddRunner.next = runner;
            oddRunner = oddRunner.next;

            runner = runner.next;
            if (runner != null) {
                evenRunner.next = runner;
                evenRunner = evenRunner.next;
                runner = runner.next;
            }
        }

        oddRunner.next = evenDummy.next;
        evenRunner.next = null;

        return oddDummy.next;
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
