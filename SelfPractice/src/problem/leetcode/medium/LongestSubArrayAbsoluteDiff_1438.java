package problem.leetcode.medium;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such
 * that the absolute difference between any two elements of this subarray is less than or equal to limit.
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4
 */
public class LongestSubArrayAbsoluteDiff_1438 {
    /**
     * The goal is to do sliding window while keeping track of the current min and max.
     * The key is to toss away min and max as we shorten the window. We use priority queue for this, and we toss away indexes that are out of the window.
     * Runtime: O(n log n)      Space: O(n)
     */
    public int longestSubarray(int[] nums, int limit) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->Integer.compare(b,a));
        int left = 0;
        int right = 0;
        int res = 0;

        while(right < nums.length){
            minHeap.add(nums[right]);       // heap.add = O(log n)
            maxHeap.add(nums[right]);

            while(Math.abs(maxHeap.peek() - minHeap.peek()) >limit){
                minHeap.remove(nums[left]);
                maxHeap.remove(nums[left]);     // heap.remove = O(log n)
                left++;
            }
            res = Math.max(right - left + 1,res);
            right++;
        }
        return res;
    }
}
