package com.leetcode.arrays.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is messed up solution
 */
public class ThreeSumNonSortedArray {
    public boolean threeSum(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                int foundElementAt = poll(nums, -(nums[i] + nums[j]));
                if ( foundElementAt != -1) {
                    return true;
                }
            }
        }

        return false;
    }

    public int poll(int[] nums, int num) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (num < nums[mid]) {
                hi = mid - 1;
            } else if (num > nums[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        ThreeSumNonSortedArray solution = new ThreeSumNonSortedArray();
        boolean foundThreeSum = solution.threeSum(nums);
        System.out.println(foundThreeSum);
    }
}