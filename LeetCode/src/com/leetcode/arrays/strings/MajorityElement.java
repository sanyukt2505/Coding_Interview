package com.leetcode.arrays.strings;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] num = {3, 3, 4, 6, 7};
        System.out.println("Majority Element: " + majorityElement(num));
    }

    private static int majorityElement(int[] num) {
        int major = num[0], count = 1;

        for (int i = 1; i < num.length; i++) {
            if (count == 0) {
                major = num[i];
                count++;
            } else if (major == num[i]) {
                count++;
            } else {
                count--;
            }
        }

        return major;
    }
}
