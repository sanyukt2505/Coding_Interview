package com.ucb.algos.ds.stacks.queues.deques;

/**
 * Created by Vijay on 2/19/16.
 */
public class DelimitersMatchTest {
    public static void main(String[] args) {
        String expression = "{({})}";
        boolean delimitersMatch = isMatched(expression);
        System.out.println("Delimiters Matched?: "+delimitersMatch);
    }

    private static boolean isMatched(String expression) {
        String opening = "({[";
        String closing = ")}]";

        Stack<Character> buffer = new LinkedStack<>();

        for (Character c : expression.toCharArray()) {
            if (opening.indexOf(c) != -1) {
                buffer.push(c);
            } else if (closing.indexOf(c) != -1) {
                if (buffer.isEmpty()) {
                    return false;
                }
                if (closing.indexOf(c) != opening.indexOf(buffer.pop())) {
                    return false;
                }
            }
        }
        return buffer.isEmpty();
    }
}
