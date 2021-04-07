package com.leetcode.stacks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Just use a queue where you "push to front" by pushing to back AND
 * then rotating the queue until the new element is at the front (i.e., size-1 times move the front element to the back).
 */
public class ImplementStackUsingSingleQueue {
    private Queue<Integer> queue = new LinkedList<>();

    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.remove());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        queue.remove();
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}
