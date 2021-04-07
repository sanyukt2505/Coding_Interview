package com.ucb.algos.ds.fundamental.ds;

/**
 * Created by Vijay on 2/8/16.
 */
public class DoublyLinkedList<E> {
    private int size = 0;
    private Node<E> header;
    private Node<E> trailer;

    public DoublyLinkedList() {
        this.header = new Node(null, null, null);
        this.trailer = new Node(null, header, null);
        this.header.setNext(this.trailer);
    }

    // Access Methods
    public int size() { return this.size; }

    public boolean isEmpty() { return this.size == 0; }

    public E first() {
        if (isEmpty()) return null;
        return this.header.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return this.trailer.getPrev().getElement();
    }

    // Public Update Methods
    public void addFirst(E e) {
        addBetween(e, this.header, this.header.getNext());
    }

    public void addLast(E e) {
        addBetween(e, this.trailer.getPrev(), this.trailer);
    }

    public E removeFirst() {
        if(isEmpty()) return null;
        return remove(this.header.getNext());
    }

    public E removeLast() {
        if(isEmpty()) return null;
        return remove(this.trailer.getPrev());
    }

    // Utility methods
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    /**
     * Node in Doubly Linked List has 2 pointers and 1 element (which is the content of the Node)
     * @param <E>
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }

        public Node(E element, Node<E> p, Node<E> n) {
            this.element = element;
            this.prev = p;
            this.next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }
}
