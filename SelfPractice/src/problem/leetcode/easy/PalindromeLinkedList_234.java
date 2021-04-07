package problem.leetcode.easy;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * Given a singly linked list, determine if it is a palindrome.
 */
public class PalindromeLinkedList_234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        /** get to the middle of the LL */
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode firstHalfEnd = slow;
        ListNode secondHalfStart = reverseList(slow.next);

        /** compare the first half and the reversed second half */
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean res = true;
        while (p2 != null) {
            if (p1.val != p2.val) {
                res = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        /** Optional: restore the list */
        firstHalfEnd.next = reverseList(secondHalfStart);
        return res;
    }

    /** reverse a LL */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    class ListNode {
        int val;
        ListNode next;
    }
}
