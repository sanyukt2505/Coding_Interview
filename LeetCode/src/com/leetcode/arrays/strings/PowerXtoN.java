package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 5/2/16.
 */
public class PowerXtoN {
    static double myPow(double x, int n) {
        if (n == 0) return 1;

        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        return n % 2 == 0 ? myPow(x*x, n/2) : x * myPow(x*x, n/2);
    }

    static double myPowIterative(double x, int n) {
        if (n == 0) return 1;
        if (x == 1) return 1;
        if (x == -1) {
            if (n % 2 == 0) return 1;
            else return -1;
        }
        if (n == Integer.MIN_VALUE) return 0;
        if (n < 0) {
            n = -n;
            x = 1/x;
        }
        double ret = 1.0;
        while (n > 0) {
            if ((n & 1) != 0)
                ret *= x;
            x = x * x;
            n = n >> 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(myPow(25, 2));
        System.out.println(myPowIterative(25, 2));
    }
}
