package com.leetcode.linkedlists;

/**
 * Created by Vijay on 2/21/16.
 */
public class SinglyLinkedList<E extends Comparable<? super E>> {
    private ListNode<E> head;
    private ListNode<E> tail;
    private int size = 0;

    // Access methods
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return this.head.getElement();
    }

    public ListNode<E> getHead() {
        if (isEmpty()) return null;
        return this.head;
    }

    public E last() {
        if (isEmpty()) return null;
        return this.tail.getElement();
    }

    public ListNode<E> getTail() {
        if (isEmpty()) return null;
        return this.tail;
    }

    // Update methods
    public SinglyLinkedList<E> addFirst(E e) {
        // Create new node and mark it as head
        this.head = new ListNode<>(e, this.head);

        // Special case: the list is empty then tail is same as head
        if (isEmpty()) {
            this.tail = this.head;
        }

        // Don't forget to increase the size
        size++;

        return this;
    }

    public SinglyLinkedList<E> addLast(E e) {
        ListNode<E> newest = new ListNode<>(e, null);

        // Special case: the list is empty the head is same as tail
        if(isEmpty()) {
            this.head = newest;
        } else {
            // List is not empty. Set next to the newest
            this.tail.setNext(newest);
        }

        // Update the tail reference to point to the new node
        this.tail = newest;

        // Don't forget to increment the size
        size++;

        return this;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        // Retrieve the element from the head node
        E element = head.getElement();
        // Point the head to next node
        this.head = this.head.getNext();
        // Don't forget to decrease the size
        size--;

        // By decreasing the size if the value is reduced to 0 then assign null to tail
        if (size == 0) {
            this.tail = null;
        }
        return element;
    }
}
