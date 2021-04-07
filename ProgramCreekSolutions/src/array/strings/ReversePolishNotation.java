package array.strings;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation
 * e.g. {"2", "1", "+", "3", "*"} = 9
 * Created by Vijay on 3/12/16.
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = new String[] {"2", "1", "-", "10", "+", "3", "*"};
        System.out.println(evalRPN(tokens));
    }

    private static int evalRPN(String[] tokens) {
        int returnVal;
        String operators = "+-*/";
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (!operators.contains(token)) {
                stack.push(token);
            } else {
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());

                switch (token) {
                    case "+" :
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-" :
                        stack.push(String.valueOf(b - a));
                        break;
                    case "*" :
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/" :
                        stack.push(String.valueOf(b / a));
                        break;
                }
            }
        }

        returnVal = Integer.valueOf(stack.pop());

        return returnVal;
    }
}
