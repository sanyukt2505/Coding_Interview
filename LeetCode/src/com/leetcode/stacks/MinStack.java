package com.leetcode.stacks;

import java.util.Stack;

/**
 * Consider using an extra stack to keep track of current minimum value.
 * push(int x): Insert x into actual stack. If the new element is greater than current minimum in the minStack, do not need to push it onto the min stack
 * pop(): Check if the popped element is the same as the current minimum. If it is, pop it off the min stack too.
 */
public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        // Insert into the main stack
        stack.push(x);

        // If the new element is smaller or equal to current minimum, push into the minStack as well
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        // Pop from main Stack stack. If it is equal to minStack.peek then pop from minStack as well.
        if (stack.pop().equals(minStack.peek())) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        // Remember: Invoke peek() on minStack and not pop
        return minStack.peek();
    }
}
