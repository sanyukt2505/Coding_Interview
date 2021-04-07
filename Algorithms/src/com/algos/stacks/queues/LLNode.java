package com.algos.stacks.queues;

/**
 * Created by Vijay on 1/28/16.
 */
public class LLNode<E> {
    LLNode<E> next;
    E data;

    public LLNode() {
        this.next = null;
        this.data = null;
    }

    public LLNode(E data) {
        this.next = null;
        this.data = data;
    }

    public LLNode(E data, LLNode<E> prevNode) {
        this(data);
        this.next = prevNode.next;
        prevNode.next = this;
    }

}
