package com.algos.recursion;

public class SumRecursive {
    public static void main(String[] args) {
        int sum  = sumRecursive(100);
        System.out.println("Sum: " + sum);
    }

    private static int sumRecursive(int N) {
        if (N == 1) return 1;
        return N + sumRecursive(N-1);
    }
}
