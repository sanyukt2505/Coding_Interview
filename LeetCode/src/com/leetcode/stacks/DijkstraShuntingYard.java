package com.leetcode.stacks;

import java.util.Stack;

/**
 * Dijkstra's algorithm for expression evaluation.
 */
public class DijkstraShuntingYard {
    private Stack<String> operatorsStack;
    private Stack<Double> numbersStack;

    public DijkstraShuntingYard() {
        this.operatorsStack = new Stack<>();
        this.numbersStack = new Stack<>();
    }

    public double evaluateExpression(String expression) {
        String[] tokenArray = expression.split(" ");

        for (String token : tokenArray) {
            if (token.equals("(")) {
                // No Op
            } else if (token.equals("+")) {
                this.operatorsStack.push(token);
            } else if (token.equals("*")) {
                this.operatorsStack.push(token);
            } else if (token.equals(")")) {
                String operation = this.operatorsStack.pop();

                if (operation.equals("+")) {
                    this.numbersStack.push(this.numbersStack.pop() + this.numbersStack.pop());
                } else if (operation.equals("*")) {
                    this.numbersStack.push(this.numbersStack.pop() * this.numbersStack.pop());
                }
            } else {
                this.numbersStack.push(Double.parseDouble(token));
            }

        }

        return this.numbersStack.pop();
    }

    public static void main(String[] args) {
        DijkstraShuntingYard expressionEvaluator = new DijkstraShuntingYard();

        String expression = "( 1 + ( ( 2 + 3 ) * ( 5 * 4 ) ) )";

        System.out.println("Result: " + expressionEvaluator.evaluateExpression(expression));
    }
}
