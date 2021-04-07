package com.leetcode.arrays.strings;

import java.util.Arrays;

/**
 * Problem: https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubaray {

    public static int maxSubArray(int[] nums) {
        // maxSoFar stores the global maximum sum
        int maxSoFar = nums[0];

        // maxEndingHere gives sum at each position
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] nums = {-4, 15, -6, 18, 2, -20};
        int maxSubArray = maxSubArray(nums);
        System.out.println("Max Subarray: " + maxSubArray);
    }
}
