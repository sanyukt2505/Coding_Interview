package com.ucb.algos.ds.stacks.queues.deques;

/**
 * Created by Vijay on 2/19/16.
 */
public interface Stack<E> {
    int size();
    boolean isEmpty();
    void push(E e);
    E top();
    E pop();
}
