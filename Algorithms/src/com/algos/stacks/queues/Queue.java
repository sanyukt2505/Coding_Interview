package com.algos.stacks.queues;

import java.util.Iterator;

/**
 * Created by Vijay on 1/28/16.
 */
public class Queue<E> implements Iterable<E> {
    private LLNode<E> first = null;
    private LLNode<E> last = null;

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(E item) {
        LLNode<E> oldLast = last;
        last = new LLNode<>(item);
        // Special case for Empty queue
        if (isEmpty()) first = last;
        else oldLast.next = last;
    }

    public E dequeue() {
        E item = first.data;
        first = first.next;
        // Special case for Empty queue
        if (isEmpty()) last = null;
        return item;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private LLNode<E> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E item = current.data;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Queue<String> wordQueue = new Queue<>();
        wordQueue.enqueue("Hello!");
        wordQueue.enqueue("How");
        wordQueue.enqueue("are");
        wordQueue.enqueue("you?");

        // We can use the following for each construct on Queue object because it is implementing Iterable interface
        for (String word : wordQueue) {
            System.out.print(word + " ");
        }
    }
}
