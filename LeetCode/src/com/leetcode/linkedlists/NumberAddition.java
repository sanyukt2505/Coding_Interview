package com.leetcode.linkedlists;

/**
 * Created by Vijay on 2/21/16.
 */
public class NumberAddition<E extends Comparable<? super E>> {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> firstList = new SinglyLinkedList<>();
        firstList = firstList.addFirst(8);
        firstList = firstList.addLast(3);
        //firstList = firstList.addLast(5);
        //firstList = firstList.addLast(7);

        SinglyLinkedList<Integer> secondList = new SinglyLinkedList<>();
        secondList = secondList.addFirst(3);
        secondList = secondList.addLast(4);
        secondList = secondList.addLast(6);
        secondList = secondList.addLast(8);

        NumberAddition<Integer> numberAddition = new NumberAddition<>();

        // 8643 + 38
        ListNode<Integer> result = numberAddition.addTwoNumbers(firstList.getHead(), secondList.getHead());

        while (result != null) {
            System.out.println(result.getElement());
            result = result.getNext();
        }
    }

    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> dummyHead = new ListNode();
        ListNode<Integer> p = l1, q = l2, curr = dummyHead;
        int carry = 0;

        while (p != null || q != null) {
            // If node is null, assume it's value to be 0
            int x = (p != null) ? p.getElement() : 0;
            int y = (q != null) ? q.getElement() : 0;

            int digit = carry + x + y;
            // Recalculate carry based on the value of digit
            carry = digit / 10;
            // Set the next node's value to be digit%10 value
            curr.setNext(new ListNode<>(digit % 10));
            // set current to next current
            curr = curr.getNext();

            if (p != null) p = p.getNext();
            if (q != null) q = q.getNext();
        }

        if (carry > 0) {
            curr.setNext(new ListNode<>(carry));
        }

        return dummyHead.getNext();
    }
}
