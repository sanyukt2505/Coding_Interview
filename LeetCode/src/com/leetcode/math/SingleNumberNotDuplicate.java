package com.leetcode.math;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 *
 * This XOR operation works because it's like XORing all the numbers by itself.
 * So if the array is {2,1,4,5,2,4,1} then it will be like we are performing this operation: ((2^2)^(1^1)^(4^4)^(5)) => (0^0^0^5) => 5.
 */
public class SingleNumberNotDuplicate {
    public static void main(String[] args) {
        int singleDigit = singleNumber(new int[]{2, 1, 4, 3, 2, 4, 1});
        System.out.println("Single number not duplicate: " + singleDigit);
    }

    public static int singleNumber(int[] nums) {
        int result = 0;

        for (int number : nums) {
            result ^= number;
        }

        return result;
    }
}
