package problem.leetcode.medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/validate-stack-sequences/
 * Given two sequences pushed and popped with distinct values, return true if and only if this could have
 * been the result of a sequence of push and pop operations on an initially empty stack.
 * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * Output: true
 */
public class ValidateStackSequence_946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        Stack<Integer> stack = new Stack<>();

        /** - keeping pushing the pushed element until popped element is on top of Stack
         *  - keeping popping the popped array element   */
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
