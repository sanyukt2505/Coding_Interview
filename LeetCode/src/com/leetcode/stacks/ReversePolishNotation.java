package com.leetcode.stacks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Evaluate the value of arithmetic expression in Reverse Polish Notation
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * e.g. ["2", "1", "+", "3", "*"] = ((2+1)*3) -> 9
 */
public class ReversePolishNotation {
  private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

  public static void main(String[] args) {
    String[] tokens = {"2", "1", "+", "3", "*"};
    System.out.println("Result: " + evalRPN(tokens));
  }

  private static int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();

    for (String token : tokens) {
      if (OPERATORS.contains(token)) {
        int a = stack.pop();
        int b = stack.pop();
        stack.push(eval(a, b, token));
      } else {
        stack.push(Integer.valueOf(token));
      }
    }

    return stack.pop();
  }

  private static Integer eval(int a, int b, String token) {
    switch (token) {
      case "+" : return a + b;
      case "-" : return b - a;
      case "*" : return a * b;
      default : return b / a;
    }
  }
}
