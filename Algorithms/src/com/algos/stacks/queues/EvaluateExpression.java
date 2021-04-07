package com.algos.stacks.queues;

import java.util.List;

/**
 * Created by Vijay on 1/29/16.
 */
public class EvaluateExpression {
    public static void main(String[] args) {
        String expression = "( 1 + ( ( 2 + 3 ) * ( 5 * 4 ) ) )";

        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.equals("(")) {
                System.out.print("* \t");
            } else if (token.equals("+")) {
                ops.push(token);
            } else if (token.equals("*")) {
                ops.push(token);
            } else if (token.equals(")")) {
                String op = ops.pop();
                if (op.equals("+")) vals.push(vals.pop() + vals.pop());
                if (op.equals("*")) vals.push(vals.pop() * vals.pop());
            } else {
                vals.push(Double.parseDouble(token));
            }
        }
        System.out.println("");
        System.out.println(vals.pop());
    }
}
