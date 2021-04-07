package com.leetcode.arrays.strings;

/**
 * Given an array of integers move zeros to end of array
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 0, 0, 3, 4, 5};
        moveZeros(nums);

        for (int num : nums) {
            System.out.println(num);
        }
    }

    private static void moveZeros(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int insertPosition = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[insertPosition++] = num;
            }
        }

        while(insertPosition < nums.length) {
            nums[insertPosition] = 0;
            insertPosition++;
        }
    }
}
