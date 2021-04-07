package com.leetcode.linkedlists;

/**
 * Created by Vijay on 4/10/16.
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode<Integer> first = new ListNode<>(1);
        ListNode<Integer> second = new ListNode<>(3);
        ListNode<Integer> third = new ListNode<>(5);
        ListNode<Integer> forth = new ListNode<>(7);

        first.setNext(second);
        second.setNext(third);
        third.setNext(forth);

        ListNode<Integer> result = reverseList(first);

        while (result != null) {
            System.out.println(result.getElement());
            result = result.getNext();
        }
    }

    /**
     * Recursive Solution
     * @param head
     * @return
     */
    private static ListNode<Integer> reverseList(ListNode<Integer> head) {
        if (head == null || head.getNext() == null) return head;

        // get second node
        ListNode<Integer> second = head.getNext();

        // set first's next to null
        head.setNext(null);

        ListNode<Integer> rest = reverseList(second);
        second.setNext(head);

        return rest;
    }

    /**
     * Iterative Solution
     * @param head
     * @return
     */
    private static ListNode<Integer> reverseListIterative(ListNode<Integer> head) {
        ListNode<Integer> newHead = null;

        while (head != null) {
            ListNode<Integer> next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        return newHead;
    }
}
