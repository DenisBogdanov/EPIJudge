package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class SortList {

    @EpiTest(testDataFile = "sort_list.tsv")
    public static ListNode<Integer> stableSortList(ListNode<Integer> head) {
        List<Integer> nums = new ArrayList<>();
        var runner = head;
        while (runner != null) {
            nums.add(runner.data);
            runner = runner.next;
        }
        nums.sort(null);
        var dummy = new ListNode<>(-1, null);
        runner = dummy;
        for (int num : nums) {
            runner.next = new ListNode<>(num, null);
            runner = runner.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SortList.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
