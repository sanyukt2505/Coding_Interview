package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 6/7/16.
 */
public class RotateArray {
    public static void rotate(int[] nums, int k) {
        if(k < 0){
            if (Math.abs(k) > nums.length) {
                k = -(Math.abs(k) % nums.length);
            }
            k = (nums.length + k ) % nums.length; //make it same as right shift
        } else {
            k = k % nums.length;
        }
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length-1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, -3);

        for (int num : nums) {
            System.out.println(num);
        }
    }
}
