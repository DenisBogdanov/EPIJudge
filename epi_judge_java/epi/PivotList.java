package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PivotList {

    public static ListNode<Integer> listPivoting(ListNode<Integer> head, int x) {
        var leftDummy = new ListNode<>(-1, null);
        var rightDummy = new ListNode<>(-1, null);
        var midDummy = new ListNode<>(-1, null);

        var leftRunner = leftDummy;
        var rightRunner = rightDummy;
        var midRunner = midDummy;

        var runner = head;
        while (runner != null) {
            if (runner.data < x) {
                leftRunner.next = runner;
                leftRunner = leftRunner.next;
            } else if (runner.data == x) {
                midRunner.next = runner;
                midRunner = midRunner.next;
            } else {
                rightRunner.next = runner;
                rightRunner = rightRunner.next;
            }

            runner = runner.next;
        }

        if (midRunner != midDummy) {
            leftRunner.next = midDummy.next;
            midRunner.next = rightDummy.next;
            rightRunner.next = null;
        } else {
            leftRunner.next = rightDummy.next;
            rightRunner.next = null;
        }

        return leftDummy.next;
    }

    public static List<Integer> linkedToList(ListNode<Integer> l) {
        List<Integer> v = new ArrayList<>();
        while (l != null) {
            v.add(l.data);
            l = l.next;
        }
        return v;
    }

    @EpiTest(testDataFile = "pivot_list.tsv")
    public static void listPivotingWrapper(TimedExecutor executor,
                                           ListNode<Integer> l, int x)
            throws Exception {
        List<Integer> original = linkedToList(l);

        final ListNode<Integer> finalL = l;
        l = executor.run(() -> listPivoting(finalL, x));

        List<Integer> pivoted = linkedToList(l);

        int mode = -1;
        for (Integer i : pivoted) {
            switch (mode) {
                case -1:
                    if (i == x) {
                        mode = 0;
                    } else if (i > x) {
                        mode = 1;
                    }
                    break;
                case 0:
                    if (i < x) {
                        throw new TestFailure("List is not pivoted");
                    } else if (i > x) {
                        mode = 1;
                    }
                    break;
                case 1:
                    if (i <= x) {
                        throw new TestFailure("List is not pivoted");
                    }
            }
        }

        Collections.sort(original);
        Collections.sort(pivoted);
        if (!original.equals(pivoted))
            throw new TestFailure("Result list contains different values");
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PivotList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
