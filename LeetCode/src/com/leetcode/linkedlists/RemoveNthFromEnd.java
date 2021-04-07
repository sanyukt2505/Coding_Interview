package com.leetcode.linkedlists;

/**
 * Single pass solution to this problem
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> firstList = new SinglyLinkedList<>();
        firstList = firstList.addFirst(1);
        firstList = firstList.addLast(3);
        firstList = firstList.addLast(5);
        firstList = firstList.addLast(7);

        ListNode<Integer> newHead = removeNthFromEnd(firstList.getHead(), 2);

        while (newHead != null) {
            System.out.println(newHead.getElement());
            newHead = newHead.getNext();
        }

        SinglyLinkedList<Integer> secondList = new SinglyLinkedList<>();
        secondList = secondList.addFirst(1);
        secondList = secondList.addLast(2);

        newHead = removeNthFromEnd(secondList.getHead(), 1);

        while (newHead != null) {
            System.out.println(newHead.getElement());
            newHead = newHead.getNext();
        }

    }

    public static ListNode<Integer> removeNthFromEnd(ListNode<Integer> head, int n) {
        if (head == null) {
            return null;
        }

        ListNode<Integer> slow = head;
        ListNode<Integer> fast = head;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        if (fast == null) {
            head = head.next;
            return head;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return head;
    }
}
