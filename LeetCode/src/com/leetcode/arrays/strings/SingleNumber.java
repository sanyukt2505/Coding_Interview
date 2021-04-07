package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 4/24/16.
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] numbers = {11, 22, 55, 44, 22, 44, 55, 11, 33, 33, 77};
        int nonDuplicate = singleNumber(numbers);
        System.out.println(nonDuplicate);
    }

    private static int singleNumber(int[] nums) {
        int result = 0;

        for (int number : nums) {
            result ^= number;
        }

        return result;
    }
}
