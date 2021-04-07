package com.algos.stacks.queues;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Vijay on 1/28/16.
 */
public class Stack<E> implements Iterable<E> {
    private LLNode<E> first = null;

    public boolean isEmpty() {
        return first == null;
    }

    public void push(E item) {
        LLNode<E> oldFirst = first;
        first = new LLNode<>(item);
        first.next = oldFirst;
    }

    public E pop() {
        E item = first.data;
        first = first.next;
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
        Stack<String> wordsStack = new Stack<>();
        wordsStack.push("Hello!");
        wordsStack.push("How");
        wordsStack.push("are");
        wordsStack.push("you?");

        // We can use the following for each construct on Stack object because it is implementing Iterable interface
        for (String word : wordsStack) {
            System.out.print(word + " ");
        }
    }
}
