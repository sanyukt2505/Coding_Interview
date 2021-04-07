package com.algos.recursion;

/**
 * Created by Vijay on 4/16/16.
 */
public class FactorialAccumulator {
    public static void main(String[] args) {
        int factorial = factorial(5);
        System.out.println("Factorial: " +  factorial);
    }

    private static int factorial(int N) {
        // Initially, the accumulator value would be 1
        return fact(1, N);
    }

    /**
     * accumulator contains the outputs from sub-problems
     */
    private static int fact(int accumulator, int n) {
        // base case
        if (n == 1) return accumulator;
        return fact(accumulator * n, n-1);
    }
}
