package problem.leetcode.medium;

import java.util.HashSet;
import java.util.Stack;

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
 * so that the resulting parentheses string is valid and return any valid string.
 * Input: s = "lee(t(c)o)de)"   Output: "lee(t(c)o)de"
 * Input: s = "))(("            Output: ""
 */
public class MinimumRemoveMakeValidParentheses_1249 {

    /**
     * Increment open count whenever you find '(' and decrement for each ')'
     * dont decrement if you see ) before any ( -- this can be done by checking if open == 0 && char == ')'
     */
    public String minRemoveToMakeValid(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else if (s.charAt(i) == ')') {
                if(open == 0)
                    continue;
                open--;
            }
            res.append(s.charAt(i));
        }

        for (int i = res.length() - 1; i >= 0; i--) {
            if(s.charAt(i) == '(' && open > 0)
                continue;
            open--;
            result.append(s.charAt(i));
        }
        return result.reverse().toString();
    }

    /**
     * 1 Keep in stack all the open parenthesis index and whenever you get ), pop one item
     * 2 in case when there is nothing to pop(), add that index to deleteSet
     * 3 also add to deleteSet whatever is left in stack (is not closed)
     * 4 loop through teh deleteSet and delete charAt those index from the input String
     */
    public String minRemoveToMakeValidStack(String s) {
        Stack<Integer> stack = new Stack();
        HashSet<Integer> toDelete = new HashSet();
        for (int i = 0; i < s.length()-1; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else if(s.charAt(i) == ')') {
                if (stack.size() == 0) {
                    toDelete.add(i);
                } else {
                    stack.pop();
                }
            }
        }

        for(Integer opened: stack) {
            toDelete.add(opened);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length()-1; i++) {
            if(!toDelete.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
