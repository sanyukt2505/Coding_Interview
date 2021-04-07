package problem.leetcode.medium;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/continuous-subarray-sum/
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has
 * a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.
 * Input: [23, 2, 6, 4, 7],  k=6        Output: True
 *
 */
public class ContinuousSubarraySumK_523 {
    /**
     * So the solution is based on theory that if modulo of two numbers by k are same,
     * then their difference is going to be divisible by k.
     * if a % k = x
     * and b % k = x
     * (a - b) % k = x -x = 0
     * here a - b = the sum between i and j.
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            // sum can go very high, so to safe guard
            // you can set " sum = rem "  -- coz rem is the only value that make any impact on the next sum
            sum += nums[i];
            int rem = sum % k;
            // checking if there is any earlier occurrence when sum % k was same as current
            if (map.containsKey(rem) && (i - map.get(rem) > 1)) {
                return true;
            } else {
                map.put(rem, i);
                // sum = rem;          // for optimization
            }
        }
        return false;
    }
}
