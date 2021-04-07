package problem.leetcode.easy;

/**
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. T
 * he linked list holds the binary representation of a number.
 *
 * Return the decimal value of the number in the linked list.
 *
 * Input: head = [1,0,1]
 * Output: 5
 * Explanation: (101) in base 2 = (5) in base 10
 */
public class BinaryNumLinkedListToInteger_1290 {
    /**
     * whenever you shift a binary to left, you basically are multiplying it with 2 and then appending (adding) 1/0
     * So if you have to convert 101(5) to 1011 (11)
     * you do 5 * 2 + 1 = 11
     */
    public int getDecimalValue(ListNode head) {
        int num = head.val;
        while (head.next != null) {
            num = num * 2 + head.next.val;
            head = head.next;
        }
        return num;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {};
    }
}
