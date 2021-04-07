package com.leetcode.linkedlists;

/**
 * Created by Vijay on 4/10/16.
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode<Integer> first = new ListNode<>(1);
        ListNode<Integer> second = new ListNode<>(3);
        ListNode<Integer> third = new ListNode<>(5);
        ListNode<Integer> forth = new ListNode<>(7);

        first.setNext(second);
        second.setNext(third);
        third.setNext(forth);

        ListNode<Integer> result = swapPairs(first);

        while(result != null) {
            System.out.println(result.getElement());
            result = result.getNext();
        }
    }

    private static ListNode<Integer> swapPairs(ListNode<Integer> head) {
        ListNode<Integer> dummy = new ListNode<>();
        // Set the next node of the dummy to be head
        dummy.setNext(head);
        ListNode<Integer> p = head;

        // Create a prev node to track a node before pair
        ListNode<Integer> prev = dummy;

        while (p != null && p.getNext() != null) {
            // create 2 temporary nodes q and r to point to the next and next to next of p
            ListNode<Integer> q = p.getNext();
            ListNode<Integer> r = p.getNext().getNext();

            // swap the pair
            prev.setNext(q);
            q.setNext(p);
            p.setNext(r);

            // update prev to p
            prev = p;

            // Update the p to point to first node in next pair to be swapped
            p = r;
        }

        return dummy.getNext();
    }
}
