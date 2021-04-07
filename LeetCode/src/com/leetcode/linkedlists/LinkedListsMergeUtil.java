package com.leetcode.linkedlists;

/**
 * Created by Vijay on 2/21/16.
 */
public class LinkedListsMergeUtil<E extends Comparable<? super E>> {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> firstList = new SinglyLinkedList<>();
        firstList = firstList.addFirst(1);
        firstList = firstList.addLast(3);
        firstList = firstList.addLast(5);
        firstList = firstList.addLast(7);

        SinglyLinkedList<Integer> secondList = new SinglyLinkedList<>();
        secondList = secondList.addFirst(2);
        secondList = secondList.addLast(4);
        secondList = secondList.addLast(6);
        secondList = secondList.addLast(8);

        LinkedListsMergeUtil<Integer> mergeUtil = new LinkedListsMergeUtil();
        ListNode<Integer> mergedList = mergeUtil.mergeTwoLists(firstList.getHead(), secondList.getHead());

        while(mergedList != null) {
            System.out.println(mergedList.getElement());
            mergedList = mergedList.getNext();
        }
    }

    public ListNode<E> mergeTwoLists(ListNode<E> l1, ListNode<E> l2) {
        // Create a placeholder dummyHead to be used as first node in the result list
        ListNode<E> dummyHead = new ListNode<>();

        // result list
        ListNode<E> result = dummyHead;

        while(l1 != null && l2 != null) {
            if (l1.getElement().compareTo(l2.getElement()) < 0) {
                result.setNext(l1);
                l1 = l1.getNext();
            } else {
                result.setNext(l2);
                l2 = l2.getNext();
            }
            result = result.getNext();
        }

        if (l1 != null) result.setNext(l1);
        if (l2 != null) result.setNext(l2);

        return dummyHead.getNext();
    }
}
