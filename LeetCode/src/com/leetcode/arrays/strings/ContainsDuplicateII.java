package com.leetcode.arrays.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vijay on 5/3/16.
 */
public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] numbers = {11, 22, 33, 11, 33, 22};
        System.out.println("Contains duplicate within 2 positions: " + containsNearbyDuplicate(numbers, 2));
        System.out.println("Contains duplicate within 1 positions: " + containsNearbyDuplicate(numbers, 1));
    }
}
