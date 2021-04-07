package com.leetcode.math;

/**
 * Created by Vijay on 3/6/16.
 */
public class SecondHighestInteger {
    public static void main(String[] args) {
        int[] nums = {11, 44, 33, 22, 55};
        int secondHighest = secondHighest(nums);
        System.out.println("Second Highest: " + secondHighest);
    }

    private static int secondHighest(int[] numbers) {
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MAX_VALUE;

        for (int number : numbers) {
            if (number >= highest) {
                secondHighest = highest;
                highest = number;
            } else if (number > secondHighest) {
                secondHighest = number;
            }
        }

        return secondHighest;
    }
}
