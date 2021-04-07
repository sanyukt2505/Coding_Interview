package com.ucb.algos.ds.recursion;

/**
 * Created by Vijay on 2/16/16.
 */
public class InefficientFibonacci {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        long sum = inefficientFibonacci(25);
        long endTime = System.nanoTime();
        System.out.println("Sum: " + sum + " Time taken in Nano Seconds: " + (endTime - startTime));
    }

    private static long inefficientFibonacci(int n) {
        if (n <= 1) {
          return n;
        } else {
          return inefficientFibonacci(n-2) + inefficientFibonacci(n-1);
        }
    }
}
