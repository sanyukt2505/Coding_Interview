package com.ucb.algos.ds.recursion;

/**
 * Created by Vijay on 2/15/16.
 */
public class Factorial {
    public static void main(String[] args) {
        int result = factorial(5);
        System.out.println("Factorial of 5: " +  result);
    }

    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 0) {
            return 1;
        } else {
            return n * factorial(n-1);
        }
    }
}
