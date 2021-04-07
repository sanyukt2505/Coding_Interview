package com.leetcode.arrays.strings;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode Question: https://leetcode.com/problems/3sum/
 * Solution: https://discuss.leetcode.com/topic/8125/concise-o-n-2-java-solution
 *  Return the indexes such that Elements in triplet (a,b,c) must be in non-descending order (i.e. a <= b <= c)
 *  The idea is to sort an input array and then run through all indices of a possible first element of a triplet.
 *  For each possible first element we make a standard bi-directional 2Sum sweep of the remaining part of the array.
 *  Also we want to skip equal elements to avoid duplicates in the answer without making a set or something like that.
 */
public class ThreeSumNonSortedArrayFacebook {

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> combos = threeSum(nums);
        System.out.println(combos);
        boolean threeSumExists = threeSumBoolean(nums);
        System.out.println("Three sum exists in nums?: " + threeSumExists);

        int[] numsSecond = {1, 2, 3};
        threeSumExists = threeSumBoolean(numsSecond);
        System.out.println("Three sum exists in numsSecond?: " + threeSumExists);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                while (lo <= hi) {
                    if (nums[lo] + nums[hi] ==  sum) {
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    } else {
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    }
                }
            }
        }

        return result;
    }

    public static boolean threeSumBoolean(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                while (lo <= hi) {
                    if (nums[lo] + nums[hi] ==  sum) {
                        lo++; hi--;
                        return true;
                    } else if (nums[lo] + nums[hi] < sum) {
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    } else {
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    }
                }
            }
        }

        return false;
    }
}
