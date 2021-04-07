package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/sort-list
 */
public class SortLinkedList_148 {
    /** Using merge sort - break list in two half, osrt them and merge the half
    Time : O(nlogn)     Space: O(logn) -- recursive call*/
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode temp = head;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        /** temp is tail, head is head - of my first half
            slow is head, fast is tail- of my second hald*/
        temp.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return merge(left, right);
    }

    /** merging 2 sorted linked list*/
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        /** at the end only one element will be left either in l1 or l2*/
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
    }
}
