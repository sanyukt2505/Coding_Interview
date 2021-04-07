package com.algos.recursion;

/**
 * Created by Vijay on 4/17/16.
 */
public class GCDEuclideanAlgorithm {
    /**
     * Iterative Approach
     */
    public static int gcdIterative(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    /**
     * Recursive Approache
     */
    public static int gcdRecursive(int num1, int num2) {
        // Base Condition
        if (num2 == 0) return num1;

        return gcdRecursive(num2, num1 % num2);
    }

    public static void main(String[] args) {
        int gcdIterative = gcdIterative(18, 12);
        System.out.println("GCD Iterative: " + gcdIterative);

        int gcdRecursive = gcdRecursive(18, 12);
        System.out.println("GCD Recursive: " + gcdRecursive);
    }
}
