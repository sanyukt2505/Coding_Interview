package com.ucb.algos.ds.stacks.queues.deques;

/**
 * Created by Vijay on 2/19/16.
 */
public interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E first();
    E dequeue();
}
