package com.ucb.algos.ds.recursion;

import java.util.Arrays;

/**
 * Created by Vijay on 2/16/16.
 */
public class EfficientFibonacci {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        long[] answer = efficientFibonacci(25);
        long endTime = System.nanoTime();
        System.out.println("Answer: "+ Arrays.toString(answer) + " Time taken in Nano Seconds: " + (endTime - startTime));
    }

    /**
     * Rather than having the method return a single value, which is the nth Fibonacci number,
       we define a recursive method that returns an array with two consecutive Fibonacci numbers {Fn,Fn−1}, using the convention F−1 = 0.
     * @param n
     * @return
     */
    private static long[] efficientFibonacci(int n) {
        if (n <= 1) {
            long[] answer = {n, 0};
            return answer;
        } else {
            long[] temp = efficientFibonacci(n-1);
            long[] answer = {temp[0] + temp[1], temp[0]};
            return answer;
        }
    }
}
