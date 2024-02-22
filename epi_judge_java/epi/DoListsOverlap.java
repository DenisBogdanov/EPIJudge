package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.HashSet;
import java.util.Set;

public class DoListsOverlap {

    public static ListNode<Integer> overlappingLists(ListNode<Integer> h1, ListNode<Integer> h2) {
        ListNode<Integer> cycle1 = IsListCyclic.hasCycle(h1);
        ListNode<Integer> cycle2 = IsListCyclic.hasCycle(h2);

        if (cycle1 == null && cycle2 == null) {
            return DoTerminatedListsOverlap.overlappingNoCycleLists(h1, h2);
        } else if (cycle1 != null && cycle2 != null) {
            if (cycle1 == cycle2) return cycle1;
            var runner = cycle2.next;
            while (runner != cycle2) {
                if (runner == cycle1) return runner;
                runner = runner.next;
            }

            return null;
        } else {
            return null;
        }
    }

    @EpiTest(testDataFile = "do_lists_overlap.tsv")
    public static void
    overlappingListsWrapper(TimedExecutor executor, ListNode<Integer> l0,
                            ListNode<Integer> l1, ListNode<Integer> common,
                            int cycle0, int cycle1) throws Exception {
        if (common != null) {
            if (l0 == null) {
                l0 = common;
            } else {
                ListNode<Integer> it = l0;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }

            if (l1 == null) {
                l1 = common;
            } else {
                ListNode<Integer> it = l1;
                while (it.next != null) {
                    it = it.next;
                }
                it.next = common;
            }
        }

        if (cycle0 != -1 && l0 != null) {
            ListNode<Integer> last = l0;
            while (last.next != null) {
                last = last.next;
            }
            ListNode<Integer> it = l0;
            while (cycle0-- > 0) {
                if (it == null) {
                    throw new RuntimeException("Invalid input data");
                }
                it = it.next;
            }
            last.next = it;
        }

        if (cycle1 != -1 && l1 != null) {
            ListNode<Integer> last = l1;
            while (last.next != null) {
                last = last.next;
            }
            ListNode<Integer> it = l1;
            while (cycle1-- > 0) {
                if (it == null) {
                    throw new RuntimeException("Invalid input data");
                }
                it = it.next;
            }
            last.next = it;
        }

        Set<Integer> commonNodes = new HashSet<>();
        ListNode<Integer> it = common;
        while (it != null && !commonNodes.contains(it.data)) {
            commonNodes.add(it.data);
            it = it.next;
        }

        final ListNode<Integer> finalL0 = l0;
        final ListNode<Integer> finalL1 = l1;
        ListNode<Integer> result =
                executor.run(() -> overlappingLists(finalL0, finalL1));

        if (!((commonNodes.isEmpty() && result == null) ||
                (result != null && commonNodes.contains(result.data)))) {
            throw new TestFailure("Invalid result");
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DoListsOverlap.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
