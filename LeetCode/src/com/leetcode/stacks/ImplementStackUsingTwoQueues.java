package com.leetcode.stacks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * When you push an element, choose one empty queue(whichever when both are empty) to add this element,
 * and then push all elements of the other queue into the chosen queue.
 * After that, the newest element is at the head of the chosen queue so that whenever you want to do pop() or top(),
 * you always get the newest element.
 *
 *  For example,
        push(1):    [ , ,1] [ , , ]

        push(2):    [ , , ] [ ,1,2]

        push(3):    [ 1, 2,3 ] [ , , ]
 */
public class ImplementStackUsingTwoQueues {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    public void push(int x) {
        if (q1.isEmpty()) {
            q1.add(x);
            for (int i = 0; i < q2.size(); i++) {
                q1.add(q2.poll());
            }
        } else {
            q2.add(x);
            for (int i = 0; i < q1.size(); i++) {
                q2.add(q1.poll());
            }
        }
    }

    public void pop() {
        if (!q1.isEmpty()) {
            q1.poll();
        } else {
            q2.poll();
        }
    }

    public int top() {
        return q1.isEmpty() ? q2.peek() : q1.peek();
    }

    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
