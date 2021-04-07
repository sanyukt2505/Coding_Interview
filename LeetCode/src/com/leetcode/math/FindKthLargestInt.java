package com.leetcode.math;

import java.util.Arrays;

/**
 * Created by Vijay on 3/6/16.
 */
public class FindKthLargestInt {
    public static void main(String[] args) {
        int[] numbers = {11, 44, 33, 22, 55};
        int kthHighest = findKthLargest(numbers, 2);
        System.out.println("kth Highest: " + kthHighest);
    }

    private static int findKthLargest(int[] numbers, int k) {
        Arrays.sort(numbers);
        return numbers[numbers.length - k];
    }
}
