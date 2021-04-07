package com.leetcode.math;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 */
public class SingleNumberNotThreeTimes {
    public static void main(String[] args) {
        int singleNumber = singleNumbers(new int[]{1, 2, 3, 1, 2, 3, 4, 1, 2, 3});
        System.out.println("The number not present thrice: " + singleNumber);
    }

    public static int singleNumbers(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }
}
