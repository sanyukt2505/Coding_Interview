package problem.leetcode.easy;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * Example 1:   Input: 1->1->2      Output: 1->2
 */
public class RemoveDuplicateFromSortedList_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            /** if prev and curr are duplicates  */
            if (curr.val == prev.val) {
                prev.next = curr.next;
            } else {
                /** not duplicates  */
                prev = curr;
            }
            curr = curr.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
    }
}
