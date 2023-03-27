package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class EvenOddListMerge {

    @EpiTest(testDataFile = "even_odd_list_merge.tsv")
    public static ListNode<Integer> evenOddMerge(ListNode<Integer> list) {
        if (list == null || list.next == null) return list;

        var even = list;
        var odd = list.next;

        var evenRunner = list;
        var oddRunner = list.next;
        var runner = list.next.next;

        while (runner != null) {
            evenRunner.next = runner;
            evenRunner = evenRunner.next;
            runner = runner.next;

            if (runner != null) {
                oddRunner.next = runner;
                oddRunner = oddRunner.next;
                runner = runner.next;
            }
        }

        oddRunner.next = null;
        evenRunner.next = odd;

        return even;
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
