package com.algos.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class FibonacciAlgorithm {

    private Map<Integer, Integer> memoizeTable; // O(1)

    public FibonacciAlgorithm(HashMap<Integer, Integer> memoizeTable) {
        this.memoizeTable = memoizeTable;
        // Base cases for memoize solution
        this.memoizeTable.put(0, 0);
        this.memoizeTable.put(1, 1);
    }

    public int fibonacciMemoize(int n) {
        // If the fibonacci value is already calculated for a number, DO NOT repeat, instead return calculated value
        if (this.memoizeTable.containsKey(n)) {
            return this.memoizeTable.get(n);
        }

        // n-1 and n-2 fibonacci by recursively calling fibonacciMemoize
        this.memoizeTable.put(n-1, fibonacciMemoize(n-1));
        this.memoizeTable.put(n-2, fibonacciMemoize(n-2));

        int calculatedNumber = this.memoizeTable.get(n-1) + this.memoizeTable.get(n-2);
        this.memoizeTable.put(n, calculatedNumber);

        return calculatedNumber;
    }

    public int naiveFibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        return naiveFibonacci(n - 1) + naiveFibonacci(n - 2);
    }

    public static void main(String[] args) {
        FibonacciAlgorithm fibonacciAlgorithm = new FibonacciAlgorithm(new HashMap<>());
        long startTime = System.nanoTime();
        System.out.println(fibonacciAlgorithm.naiveFibonacci(30));
        long endTime = System.nanoTime();
        System.out.println("Time Needed for Recursive: " + (endTime - startTime));

        System.out.println();

        startTime = System.nanoTime();
        System.out.println(fibonacciAlgorithm.fibonacciMemoize(30));
        endTime = System.nanoTime();
        System.out.println("Time Needed for Memoize Solution: " + (endTime - startTime));
    }
}
