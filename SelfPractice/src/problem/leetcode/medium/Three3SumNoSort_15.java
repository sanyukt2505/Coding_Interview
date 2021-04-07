package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/3sum/solution/
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 1)  Use another hashset dups to skip duplicates in the outer loop. This case is handled when the array is sorted.
 * 2) Instead of re-populating a hashset every time in the inner loop, we can use a hashmap and populate it once.
 *      Values in the hashmap will indicate whether we have encountered that element in the current iteration.
 *      When we process nums[j] in the inner loop, we set its hashmap value to i.
 *      This indicates that we can now use nums[j] as a complement for nums[i].
 */
public class Three3SumNoSort_15 {

    public List<List<Integer>> threeSumHashSet(int[] nums) {
        // set for the result to avoid any duplicates
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; ++i)
            // checking to see if the set already have the current number
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    // adding i in seen to mark that, we found the complement in this iteration
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList<>(res);
    }

    /**  2 Pointers approach. needs array to be sorted first.
     * For the main function:
     *      Sort the input array nums.
     *      Iterate through the array:
     *          If the current value is greater than zero, break from the loop. Remaining values cannot sum to zero.
     *          If the current value is the same as the one before, skip it.
     *          Otherwise, call twoSumII for the current position i.
     *
     * For twoSumII function:
     *      Set the low pointer lo to i + 1, and high pointer hi to the last index.
     *      While low pointer is smaller than high:
     *          If sum of nums[i] + nums[lo] + nums[hi] is less than zero, increment lo.
     *          If sum is greater than zero, decrement hi.
     *          Otherwise, we found a triplet:
     *              Add it to the result res.
     *              Decrement hi and increment lo.
     *              Increment lo while the next value is the same as before to avoid duplicates in the result.
     * Return the result res.
     */
    public List<List<Integer>> threeSum2Pointer(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        }
        return res;
    }
    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                // looping through the duplicates
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }

}
