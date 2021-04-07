package com.leetcode.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Vijay on 2/23/16.
 */
public class ValidParentheses {
    private static final Map<Character, Character> map =
            new HashMap<Character, Character>() {{
                put('(', ')');
                put('{', '}');
                put('[', ']');
            }};

    public boolean isValid(String s) {
        // Create stack to contain the characters
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                // Character c is one of the opening parentheses, push into the stack
                stack.push(c);
            } else if (stack.isEmpty() || map.get(stack.pop()) != c) {
                // Stack is empty OR
                // The Character c is not one of the opening parentheses. And the value corresponding to popped key (character value returned by stack.pop()) is NOT c.
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        System.out.println("is ({[]}) valid parentheses?: " + validParentheses.isValid("({[]})"));
    }
}
