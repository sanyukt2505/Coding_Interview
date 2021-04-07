package com.leetcode.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//Abstracted out interface for the flexibility to introduce new operators as and when needed
interface Operator {
  int eval(int x, int y);
}

public class ReversePolishNotationRefactored {
  private static final Map<String, Operator> OPERATORS =
          new HashMap<String, Operator>() {{
            put("+", (x, y) -> x + y);
            put("-", (x, y) -> y - x);
            put("*", (x, y) -> x * y);
            put("/", (x, y) -> y / x);
          }};

  private static int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();

    for (String token : tokens) {
      if (OPERATORS.containsKey(token)) {
        int x = stack.pop();
        int y = stack.pop();
        stack.push(OPERATORS.get(token).eval(x, y));
      } else {
        stack.push(Integer.parseInt(token));
      }
    }

    return stack.pop();
  }

  public static void main(String[] args) {
    String[] tokens = {"2", "1", "+", "3", "*"};
    System.out.println("Result: " + evalRPN(tokens));
  }
}
