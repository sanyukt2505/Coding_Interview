package com.leetcode.math;

/**
 * Solution to the problem: https://leetcode.com/problems/powx-n/
 */
public class PowXToN {
    public static double myPow(double x, int n) {
        if (n == 0) return 1;

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        return n % 2 == 0 ? myPow(x * x, n/2) : x * myPow(x * x, n/2);
    }

    public static void main(String[] args) {
        double result = myPow(9, -2);
        System.out.println("9 pow -2: " + result);

        result = myPow(9, 2);
        System.out.println("9 pow 2: " + result);
    }
}
