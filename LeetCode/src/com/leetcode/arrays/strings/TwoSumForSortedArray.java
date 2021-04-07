package com.leetcode.arrays.strings;

/**
 * Given: A sorted array
 * The function twoSum should return the indices of the two numbers such that they add up to the target
 * index1 must be less than index 2.
 * Please note that: your returned answers (both index1 and index2 are NOT zero based
 */
public class TwoSumForSortedArray {
    public static void main(String[] args) {
        int[] numbers = {0, 2, 3, 4, 5, 7};
        int[] results = twoSum(numbers, 6);
        for (int result: results) {
            System.out.println(result);
        }
    }

    private static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int j = rank(numbers, target - numbers[i]);
            if (j != -1) {
                return new int[] {i + 1, j + 1};
            }
        }
        throw new RuntimeException("No two sum solution");
    }

    /**
     * Binary Search for key whose value is (target - value) at current index in array
     * @param numbers
     * @param key
     * @return
     */
    private static int rank(int[] numbers, int key) {
        int lo = 0, hi = numbers.length-1;

        while(lo <= hi) {
            int mid = lo + (hi-lo)/2;

            if (key < numbers[mid]) {
                hi = mid - 1;
            } else if(key > numbers[mid]) {
                lo = mid + 1;
            } else return mid;
        }

        return -1;
    }

}
