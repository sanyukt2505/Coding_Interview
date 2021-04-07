package problem.leetcode.medium;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/count-number-of-nice-subarrays/
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 * Return the number of nice sub-arrays.
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 *
 * Solution:
 *  - Replace all even number by 0 and odd numbers by 1
 *  - Now the problem can be solved as SubArray sum - https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class NiceSubarraysNumber_1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        //Replace all odd by 1 and even by 0
        for(int i=0; i<nums.length; i++){
            nums[i] = (nums[i] %2 == 0) ? 0 : 1;
        }

        // problem becomes number of subarrays with sum = k
        // [1,1,0,1,1]
        HashMap<Integer,Integer> map = new HashMap<>();
        int res = 0;
        int sum = 0;//cumulative sum
        map.put(0,1);
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return res;
    }
}
