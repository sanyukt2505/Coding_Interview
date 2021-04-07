package com.cci.linkedlists;

/**
 * Created by Vijay on 1/3/16.
 */
public class SLLNode<E> {
    SLLNode<E> next;
    E data;

    public SLLNode() {
        this.next = null;
        this.data = null;
    }

    public SLLNode(E data) {
        this.next = null;
        this.data = data;
    }

    public SLLNode(E theData, SLLNode<E> prevNode) {
        this(theData);
        this.next = prevNode.next;
        prevNode.next = this;
    }

    public void appendToTail(E theData) {
        SLLNode<E> sllNode = new SLLNode<>(theData);

        SLLNode<E> n = this;
        while (n.next != null) {
            n = n.next;
        }

        n.next = sllNode;
    }
}
