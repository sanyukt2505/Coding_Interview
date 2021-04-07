package com.leetcode.linkedlists;

public class ListNode<E extends Comparable<? super E>>{
    public E element;
    public ListNode<E> next;

    public ListNode() {
        this.element = null;
        this.next = null;
    }

    public ListNode(E element) {
        this.element = element;
        this.next = null;
    }

    public ListNode(E element, ListNode<E> next) {
        this.element = element;
        this.next = next;
    }

    public E getElement() {
        return element;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setNext(ListNode<E> next) {
        this.next = next;
    }

}
