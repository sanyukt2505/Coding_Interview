package com.leetcode.arrays.strings;

/**
 * Leetcode problem: https://leetcode.com/problems/product-of-array-except-self/
 * Solution: https://discuss.leetcode.com/topic/19033/my-simple-java-solution
 * Strategy: Use tmp to store temporary multiply result by two directions. Then fill it into result. Bingo!
 */
public class ProductExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0, tmp = 1; i < nums.length; i++) {
            result[i] = tmp;
            tmp *= nums[i];
        }

        for (int i = nums.length - 1, tmp = 1; i >= 0; i--) {
            result[i] *= tmp;
            tmp *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4};

        int[] result = productExceptSelf(nums);

        for (int i : result) {
            System.out.println(i);
        }
    }
}
