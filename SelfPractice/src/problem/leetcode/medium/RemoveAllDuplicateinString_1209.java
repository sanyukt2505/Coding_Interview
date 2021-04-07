package problem.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 * https://www.youtube.com/watch?v=afzSFD7wOvg
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and
 * removing them causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * Input: s = "deeedbbcccbdaa", k = 3               Output: "aa"
 * Input: s = "pbbcggttciiippooaais", k = 2         Output: "ps"
 */
public class RemoveAllDuplicateinString_1209 {
    // create a custom node to store a char and its count
    public class Node {
        char ch;
        int count;

        public Node (char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public String removeDuplicates(String s, int k) {
        int len = s.length();
        /** use a stack to keep track of the char and the numOfChar occourences till now*/
        Deque<Node> stack = new ArrayDeque<>();

        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            Node prev = stack.peek();

            /** if prev char count == k-1, then pop that char
            // else increment the count, if it is same char
            // else push the current char with count=1   */
            if (!stack.isEmpty() && curr == prev.ch) {
                if (prev.count == k-1) {
                    stack.pop();
                } else {
                    prev.count++;
                }
            } else {
                stack.push(new Node(curr, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            for (int i = 0; i < curr.count; i++) {
                sb.append(curr.ch);
            }
        }
        return sb.reverse().toString();
    }
}
