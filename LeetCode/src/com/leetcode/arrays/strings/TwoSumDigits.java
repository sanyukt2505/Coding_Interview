package com.leetcode.arrays.strings;

import java.util.HashSet;
import java.util.Set;

public class TwoSumDigits {

    public static int[] twoSum(int[] numbers, int target) {
        Set<Integer> set = new HashSet<>();

        for (int num : numbers) {
            if (set.contains(target - num)) {
                return new int[] {num, target - num};
            } else {
                set.add(num);
            }
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        int[] numbers = {0, 2, 4, 3, 5, 7};
        int[] results = twoSum(numbers, 6);
        for (int result: results) {
            System.out.println(result);
        }
    }
}
