package com.algos.recursion;

/**
 * Created by Vijay on 4/16/16.
 */
public class Factorial {
    public static void main(String[] args) {
        int factorial = factorial(10);
        System.out.println("Factorial: " + factorial);
    }

    private static int factorial(int N) {
        if (N < 0) throw new IllegalArgumentException("Invalid Input");

        if (N == 1) return 1;

        return N * factorial(N - 1);
    }
}
