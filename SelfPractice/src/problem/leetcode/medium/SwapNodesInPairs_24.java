package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.
 * Input: head = [1,2,3,4]      Output: [2,1,4,3]
 */
public class SwapNodesInPairs_24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = head;
        ListNode curr = head.next;
        ListNode tmp = swapPairs(curr.next);
        curr.next = prev;
        prev.next = tmp;
        return curr;
    }

    class ListNode {
        ListNode curr;
        ListNode next;
    }
}
