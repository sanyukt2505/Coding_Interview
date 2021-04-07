package com.ucb.algos.ds.fundamental.ds;

/**
 * Created by Vijay on 2/8/16.
 */
public class CircularlyLinkedList<E> {
    private Node<E> tail;
    private int size = 0;

    public CircularlyLinkedList() {}

    // Access Methods
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E first() {
        if(isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if(isEmpty()) return null;
        return tail.getElement();
    }

    // Rotate the first element to the back of the list
    public void rotate() {
        if (tail != null) {
            tail = tail.getNext(); // The old head becomes new tail
        }
    }

    // Update Methods
    public void addFirst(E e) {
        if (size == 0) {
            this.tail = new Node<>(e, null);
            this.tail.setNext(tail); // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e) {
        addFirst(e);            // insert new element at the front of the list
        tail = tail.getNext();  // Now the new element becomes the tail
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) tail = null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

    /**
     * Having a nested Node class provides strong encapsulation
     * @param <E>
     */
    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return this.element;
        }

        public Node<E> getNext() {
            return this.next;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
