package com.leetcode.linkedlists;

/**
 * Created by Vijay on 4/22/16.
 */
public class DeleteDuplicatesInSortedList {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> firstList = new SinglyLinkedList<>();
        firstList = firstList.addFirst(1);
        firstList = firstList.addLast(2);
        firstList = firstList.addLast(2);
        firstList = firstList.addLast(3);

        ListNode<Integer> newHead = deleteDuplicates(firstList.getHead());

        while (newHead != null) {
            System.out.println(newHead.getElement());
            newHead = newHead.getNext();
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode list = head;
        while (list.next != null)
        {
            if (list.element == list.next.element)
                list.next = list.next.next;
            else
                list = list.next;
        }

        return head;
    }
}
