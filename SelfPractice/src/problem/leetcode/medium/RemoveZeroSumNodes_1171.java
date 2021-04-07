package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
 * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences
 * Input: head = [1,2,-3,3,1]   Output: [3,1]
 * Input: head = [1,2,3,-3,4]   Output: [1,2,4]
 */
public class RemoveZeroSumNodes_1171 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // Map to store the sums and node until a given point
        Map<Integer,ListNode> m = new HashMap<>();
        int prefixsum = 0;
        ListNode cur = dummy;
        while(cur != null)
        {
            /** if the cumulative sum till i and j index is same
             * then the elements between i and j, add upto 0 */
            prefixsum += cur.val;
            if(m.containsKey(prefixsum))
            {
                m.get(prefixsum).next = cur.next;
                m.clear();
                prefixsum = 0;
                cur = dummy;
            }
            else{
                m.put(prefixsum, cur);
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
