package com.leetcode.arrays.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * The function twoSum should return the indices of the two numbers such that they add up to the target
 * index1 must be less than index 2.
 * Please note that: your returned answers (both index1 and index2 are NOT zero based
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] numbers = {0, 2, 4, 3, 5, 7};
        int[] results = twoSum(numbers, 6);
        for (int result: results) {
            System.out.println(result);
        }
    }

    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int x = numbers[i];

            if (map.containsKey(target - x)) {
                // The plus 1 for indices is bit confusing here. Keep in mind.
                return new int[]{map.get(target - x) + 1, i + 1};

                // If you need 0 based response, you would return this
                // return new int[]{map.get(target - x), i};
            }
            map.put(x, i);
        }

        throw new RuntimeException("No two sum solution");
    }
}
