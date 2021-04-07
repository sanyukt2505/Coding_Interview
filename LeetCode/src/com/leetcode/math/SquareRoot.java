package com.leetcode.math;

/**
 * LeetCode problem: https://leetcode.com/problems/sqrtx/
 * Solution: https://discuss.leetcode.com/topic/8680/a-binary-search-solution
 */
public class SquareRoot {
    public static int sqrt(int x) {
        if (x == 0) return 0;
        int left = 1, right = x;

        while (true) {
            int mid = left + (right-left)/2;

            if (mid > x/mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x/(mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            }
        }
    }

    public static void main(String[] args) {
        int sqrt = sqrt(25);
        System.out.println("sqrt of 25: " + sqrt);
    }
}
