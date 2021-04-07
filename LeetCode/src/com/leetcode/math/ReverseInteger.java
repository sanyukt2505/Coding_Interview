package com.leetcode.math;

/**
 * Created by Vijay on 2/1/16.
 */
public class ReverseInteger {
    private static int reverse(int x) {
        int ret = 0;
        while (x != 0) {
            if (Math.abs(ret) > 214748364) {
                return 0;
            }
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(reverse(6543));
        System.out.println(Integer.MAX_VALUE);
    }
}
