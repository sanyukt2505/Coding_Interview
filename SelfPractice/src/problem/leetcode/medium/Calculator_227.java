package problem.leetcode.medium;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Input: "3+2*2"       Output: 7
 * Input: " 3/2 "       Output: 1
 * Input: " 3+5 / 2 "   Output: 5
 */
public class Calculator_227 {

    public int calculate(String s) {
        Stack<Integer> operand = new Stack<>();
        int current = 0;
        char prevOperator = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                current = current * 10 + ch - '0';
            }
            //if its not a digit or space then go in this block.
            //also if it is last character then go in this block and finish up last operation.
            if (i == s.length() - 1 || (ch != ' ' && !Character.isDigit(ch))) {
                if (prevOperator == '+') {
                    operand.push(current);
                } else if (prevOperator == '-') {
                    operand.push(-current);
                } else if (prevOperator == '/') {
                    operand.push(operand.pop() / current);
                } else {
                    operand.push(operand.pop() * current);
                }
                prevOperator = ch;
                current = 0;
            }
        }
        int result = 0;
        while (!operand.isEmpty()) {
            result += operand.pop();
        }
        return result;
    }

    public static String calculatePlusMinus(String exp) {
        int len = exp.length();
        Integer prev = 0;
        Integer prevExp = 0;
        Integer currNum = 0;
        char prevOpr = '+';

        for (int i=0; i<len; i++) {
            char curr = exp.charAt(i);
            if (Character.isDigit(curr)) {
                currNum = currNum * 10 + curr - '0';
            }

            if (i == exp.length() -1 || !Character.isDigit(curr)) {
                if(prevOpr == '+' ) {
                    prevExp = prevExp + currNum;
                } else if(prevOpr == '-') {
                    prevExp = prevExp - currNum;
                }
                prevOpr = curr;
                currNum = 0;
            }
        }
        return prevExp.toString();
    }

    public static void main(String[] argv) {
        String expression1 = "6+9-12"; // = 3
        String expression2 = "1+2-3+4-5+6-7"; // = -2
        String expression3 = "100+200+300"; // = 600
        String expression4 = "1-2-3-0"; // = -4
        String expression5 = "255"; // = 255

        System.out.println(calculatePlusMinus(expression1));

    }
}
