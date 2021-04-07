package com.ucb.algos.ds.fundamental.ds;

/**
 * Created by Vijay on 2/8/16.
 */
public class SinglyLinkedList<E> implements Cloneable {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public SinglyLinkedList() {}

    // Access Methods
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public E first() {
        if(isEmpty()) return null;
        return head.getElement();
    }

    public E last() {
        if(isEmpty()) return null;
        return tail.getElement();
    }

    // Update Methods
    public void addFirst(E e) {
        // Create new node and mark it as head
        this.head = new Node<>(e, head);
        // Special case: If size == 0 then tail is same as head
        if (size == 0) {
            this.tail = this.head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            this.head = newest;
        } else {
            tail.setNext(newest);
        }
        // Update the tail reference to point to the new node
        this.tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E element = head.getElement();
        head = head.getNext();
        size--;
        // If list had only one node and that is removed, the tail also should be pointing to null as list is empty now
        if (size == 0) {
            this.tail = null;
        }
        return element;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SinglyLinkedList other = (SinglyLinkedList) obj;
        if (this.size != other.size) return false;
        Node walkA = this.head;
        Node walkB = other.head;
        while (walkA != null) {
            if (!walkA.getElement().equals(walkB.getElement())) return false;
            walkA = walkA.getNext();
            walkB = walkB.getNext();
        }
        // If control reached here: Everything matched successfully
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();

        if (this.size > 0) {
            other.head = new Node<>(this.head.getElement(), null);
            Node<E> walk = this.head.getNext();
            Node<E> otherTail = other.head;

            while (walk != null) {
                Node<E> newest = new Node<>(walk.getElement(), null);
                otherTail.setNext(newest);
                otherTail = newest;
                walk = walk.getNext();
            }
        }

        return other;
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
