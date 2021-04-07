package com.leetcode.linkedlists;

/**
 * Created by Vijay on 2/3/16.
 */
public class CycleInSinglyLinkedList {
    public static void main(String[] args) {
        ListNode<Integer> first = new ListNode<>(1);
        CycleInSinglyLinkedList cycle = new CycleInSinglyLinkedList();
        first = cycle.createSampleSLList(first);
        boolean isCyclePresent;

        // check if cycle is there
        isCyclePresent = hasCycle(first);
        System.out.println("Cycle in Singly Linked List?: " + isCyclePresent);
    }

    private static boolean hasCycle(ListNode<Integer> head) {
        if (head == null || head.getNext() == null) {
            return false;
        }

        ListNode<Integer> fast = head, slow = head;

        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if (fast == slow) return true;
        }

        return false;
    }

    private ListNode<Integer> createSampleSLList(ListNode<Integer> first) {
        ListNode<Integer> secondNode = new ListNode<>(2);
        first.setNext(secondNode);
        ListNode<Integer> thirdNode = new ListNode<>(3);
        secondNode.setNext(thirdNode);
        ListNode<Integer> fourthNode = new ListNode<>(4);
        thirdNode.setNext(fourthNode);
        ListNode<Integer> fifthNode = new ListNode<>(5);
        fourthNode.setNext(fifthNode);
        // Create a cycle here
        // fifthNode.setNext(secondNode);
        return first;
    }
}
