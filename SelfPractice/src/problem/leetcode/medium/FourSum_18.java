package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/4sum/
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums
 * such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 */
public class FourSum_18 {
    /**
     * 2 Pointers implementation
     */
    public List<List<Integer>> fourSum2Pointers(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum2Pointers(nums, target, 0, 4);
    }
    public List<List<Integer>> kSum2Pointers(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // Check if the sum of k smallest values is greater than target, or the sum of k largest values is smaller than target
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;
        // If k equals 2, call twoSum and return the result.
        if (k == 2)
            return twoSum2Pointers(nums, target, start);
        for (int i = start; i < nums.length; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {
                /**
                 * Recursively call kSum with start = i + 1, k = k - 1, and target - nums[i].
                 * For each returned set: Include the current value nums[i] into set. Add set to the result res.
                 */
                for (List set : kSum2Pointers(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
            }
        }
        return res;
    }
    public List<List<Integer>> twoSum2Pointers(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum < target || (lo > start && nums[lo] == nums[lo - 1]))
                ++lo;
            else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1]))
                --hi;
            else
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
        }
        return res;
    }
//  ---------------------------------------------------------------------------------------------
//  HashSet Implementation
    public List<List<Integer>> fourSumHashSet(int[] nums, int target) {
        Arrays.sort(nums);
        return kSumHashSet(nums, target, 0, 4);
    }
    public List<List<Integer>> kSumHashSet(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            return res;
        if (k == 2)
            return twoSumHashSet(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i])
                for (List set : kSumHashSet(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(set);
                }
        return res;
    }
    public List<List<Integer>> twoSumHashSet(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int i = start; i < nums.length; ++i) {
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i])
                if (seen.contains(target - nums[i]))
                    res.add(Arrays.asList(target - nums[i], nums[i]));
            seen.add(nums[i]);
        }
        return res;
    }
}
