package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/
 * Given an array of integers arr and an integer target.
 * You have to find two non-overlapping sub-arrays of arr each with sum equal target.
 * There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.
 * Input: arr = [3,1,1,1,5,1,2,1], target = 3       Output: 3
 */
public class TwoSubArrayAddingTargetSum_1477 {
    /**
     * Use sliding window to figure out subarrays that add up to target.
     * As we go, we want to store in "dp" the length of the shortest subarray we've seen up to the current "right"
     * index. Every time we find a new subarray, we add its length to the length of the shortest non-overlapping
     * subarray to its left (i.e. dp[left - 1] ).
     * We will try to minimize this computation as we go (variable "result").
     */
    public int minSumOfLengths(int[] arr, int target) {
        int len = arr.length;
        if (len < 2) {
            return -1;
        }
        /** store in "dp" the length of the shortest subarray we've seen up to the current "right" */
        int[] dp = new int[len];
        dp[0] = Integer.MAX_VALUE;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int sum =0;

        for (int right = 0; right < len; right++) {
            if (right > 0) {
                dp[right] = dp[right - 1];
            }
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                /** store in dp[right] the smallest length we have seen so far */
                dp[right] = Math.min(right - left + 1, dp[right]);
                /** If we found an subarray to the left that does not overlap with the current subarray  */
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    minLen = Math.min(right - left + 1 + dp[left - 1], minLen);
                }
                sum -= arr[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
