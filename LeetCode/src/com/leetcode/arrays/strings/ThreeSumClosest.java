package com.leetcode.arrays.strings;

import java.util.Arrays;

/**
 * Problem: https://leetcode.com/problems/3sum-closest/
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 */
public class ThreeSumClosest {
  public static void main(String[] args) {
    int[] numbers = {0, 2, 3, 4, 5, 7};
    int result = threeSumClosest(numbers, 20);
    System.out.println("ThreeSumClosest: " + result);
  }

  public static int threeSumClosest(int[] nums, int target) {
    int min = Integer.MAX_VALUE;
    int result = 0;

    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i++) {
      // Use two pointers which operate from alternate ends of the array
      int j = i + 1;
      int k = nums.length - 1;

      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        int diff = Math.abs(sum - target);

        if (diff == 0) return sum;

        if (diff < min) {
          min = diff;
          result = sum;
        }

        if (sum <= target) {
          j++;
        } else {
          k--;
        }
      }
    }
    return result;
  }
}
