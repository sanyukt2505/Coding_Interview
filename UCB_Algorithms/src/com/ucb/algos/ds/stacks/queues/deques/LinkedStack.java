package com.ucb.algos.ds.stacks.queues.deques;

import com.ucb.algos.ds.fundamental.ds.SinglyLinkedList;

/**
 * Adapter Pattern:
 *  Define a new class in such a way that it contains a hidden instance of an existing class as a hidden field
 *  AND THEN implement each method of new class using methods of the hidden instance
 */
public class LinkedStack<E> implements Stack<E> {
    private SinglyLinkedList<E> list =  new SinglyLinkedList<>();

    public LinkedStack() {}

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }
}
