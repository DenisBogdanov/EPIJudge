package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DoTerminatedListsOverlap {

    public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        if (list1 == null || list2 == null) return null;

        ListNode<Integer> runner1 = list1;
        ListNode<Integer> runner2 = list2;

        boolean changedList = false;

        while (true) {
            if (runner1 == runner2) return runner1;

            runner1 = runner1.next;
            if (runner1 == null) {
                if (changedList) return null;
                changedList = true;
                runner1 = list2;
            }

            runner2 = runner2.next;
            if (runner2 == null) {
                runner2 = list1;
            }
        }
    }

    @EpiTest(testDataFile = "do_terminated_lists_overlap.tsv")
    public static void
    overlappingNoCycleListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                                   ListNode<Integer> l1, ListNode<Integer> common)
            throws Exception {
        if (common != null) {
            if (l0 != null) {
                ListNode<Integer> i = l0;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l0 = common;
            }

            if (l1 != null) {
                ListNode<Integer> i = l1;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l1 = common;
            }
        }

        final ListNode<Integer> finalL0 = l0;
        final ListNode<Integer> finalL1 = l1;
        ListNode<Integer> result =
                executor.run(() -> overlappingNoCycleLists(finalL0, finalL1));

        if (result != common) {
            throw new TestFailure("Invalid result");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoTerminatedListsOverlap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
