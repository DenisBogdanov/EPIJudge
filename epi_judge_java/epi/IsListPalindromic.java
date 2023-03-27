package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Objects;

public class IsListPalindromic {
    private static int testId = 0;

    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    public static boolean isLinkedListAPalindrome(ListNode<Integer> list) {
        testId++;
        if (list == null || list.next == null) return true;

        var slow = list;
        var fast = list;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        var left = list;
        var right = reverse(slow);

        while (left != null && right != null) {
            if (!Objects.equals(left.data, right.data)) return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }

    private static ListNode<Integer> reverse(ListNode<Integer> head) {
        var dummy = new ListNode<>(-1, head);

        while (head != null && head.next != null) {
            ListNode<Integer> temp = head.next;
            head.next = head.next.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsListPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
