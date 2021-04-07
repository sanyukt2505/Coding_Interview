package problem.leetcode.medium;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/solution/
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * Input:nums = [3,1,2,-1,-2,6,-3], k = 3
 * Output: 6
 */
public class SubarraySumEqualsK_560 {
    /**
     * Instead of considering all the start and end points and then finding the sum for each subarray
     * corresponding to those points, we can directly find the sum on the go while considering different end points.
     * i.e. We can choose a particular start point and while iterating over the end points,
     * we can add the element corresponding to the end point to the sum formed till now.
     * Whenver the sum equals the required k value, we can update the count value.
     *
     * O(n^2)
     */
    public int subarraySum(int[] nums, int k) {
        int resCount = 0;

        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum = sum + nums[end];
                if (sum == k)
                    resCount++;
            }
        }
        return resCount;
    }

    /**
     * The idea behind this approach is as follows: If the cumulative sum(repreesnted by sum[i] for sum upto i-th index)
     * upto two indices is the same, the sum of the elements lying in between those indices is zero.
     * Extending the same thought further, if the cumulative sum upto two indices, say i and j is at a difference of k
     * i.e. if sum[i] - sum[j] = k, then sum of elements lying between indices i and j is k.
     *
     * O(n)
     */
    public int subarraySumUsingMap(int[] nums, int k) {
        int resCount = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap();
        map.put(0,1);

        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if(map.containsKey(sum - k)) {
                resCount += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return resCount;
    }
}
