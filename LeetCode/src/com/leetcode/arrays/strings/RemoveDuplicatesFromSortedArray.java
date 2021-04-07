package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 6/2/16.
 */
public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        int i = nums.length > 0 ? 1 : 0;

        for (int n : nums) {
            if (n > nums[i-1]) {
                nums[i++] = n;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        int uniqueElements = removeDuplicates(nums);
        System.out.println("Unique elements in the array: " + uniqueElements);
    }
}
