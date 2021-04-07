package com.leetcode.arrays.strings;

/**
 * Created by Vijay on 2/20/16.
 */
public class ThreeSumSortedArray {
    public static void main(String[] args) {
        int[] numbers = {0, 2, 3, 4, 5, 7};
        int[] results = threeSum(numbers, 6);
        for (int result: results) {
            System.out.println(result);
        }
    }

    private static int[] threeSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i +1; j < numbers.length; j++) {
                int rank = rank(numbers, (target - (numbers[i] + numbers[j])));
                if (rank != -1) {
                    return new int[]{i, j, rank};
                }
            }
        }
        throw new RuntimeException("No three numbers sum equal to target");
    }

    private static int rank(int[] numbers, int target) {
        int lo = 0, hi = numbers.length;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target < numbers[mid]) {
                hi = mid - 1;
            } else if (target > numbers[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
