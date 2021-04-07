package com.ucb.algos.ds.stacks.queues.deques;

/**
 * Created by Vijay on 2/27/16.
 */
public interface Deque<E> {
    int size();

    boolean isEmpty();

    E first();

    E last();

    void addFirst();

    void addLast();

    E removeFirst();

    E removeLast();
}
