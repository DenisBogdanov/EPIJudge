package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsListPalindromic {

    @EpiTest(testDataFile = "is_list_palindromic.tsv")
    public static boolean isLinkedListAPalindrome(ListNode<Integer> head) {
        if (head == null || head.next == null) return true;

        var fast = head.next;
        var slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = slow.next;
        slow.next = null;
        fast = reverse(fast);

        while (head != null && fast != null) {
            if (!head.data.equals(fast.data)) return false;
            head = head.next;
            fast = fast.next;
        }

        return true;
    }

    private static ListNode<Integer> reverse(ListNode<Integer> head) {
        var runner = head;

        while (runner != null) {
            var next = runner.next;
            runner.next = head;
            head = runner;
            runner = next;
        }

        return head;
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
