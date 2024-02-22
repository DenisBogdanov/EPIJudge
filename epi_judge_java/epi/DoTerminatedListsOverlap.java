package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DoTerminatedListsOverlap {

    public static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> h1, ListNode<Integer> h2) {
        if (h1 == null || h2 == null) return null;

        var r1 = h1;
        var r2 = h2;

        int times = 0;

        while (r1 != null || r2 != null) {
            if (r1 == null) {
                r1 = h2;
                times++;
            }

            if (r2 == null) {
                r2 = h1;
                times++;
            }

            if (times > 2) break;

            if (r1 == r2) return r1;

            r1 = r1.next;
            r2 = r2.next;
        }

        return null;
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
