package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/jump-game/
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * Input: nums = [2,3,1,1,4]        Output: true
 */
public class JumpGame_45_55 {
    public boolean canJumpGreedy(int[] nums) {
        int n = nums.length;
        /** start from the start and set that position = maxPos
         check max position one could reach starting from index <= i */
        int maxPos = nums[0];
        for (int i = 1; i < n; ++i) {
            // if one could't reach this point
            if (maxPos < i) {
                return false;
            }
            maxPos = Math.max(maxPos, nums[i] + i);
        }
        return true;
    }

    /** ------  Returns minimum number of jumps to reach arr[h] from arr[l]  ---------
     * Backtracking,  O(2^N)  -- finding which combination will give me minJump
     */
    static int minJumpsBacktrack(int arr[], int low, int high) {
        /** Base case: when source and destination are same  */
        if (high == low)
            return 0;

        /** When nothing is reachable from the given source  */
        if (arr[low] == 0)
            return Integer.MAX_VALUE;

        /** Traverse through all the points reachable from arr[l].
        // Recursively get the minimum number of jumps
        // needed to reach arr[h] from these reachable points.    */
        int min = Integer.MAX_VALUE;
        for (int i = low + 1; i <= high && i <= low + arr[low]; i++) {
            int jumps = minJumpsBacktrack(arr, i, high);
            if (jumps != Integer.MAX_VALUE && jumps + 1 < min)
                min = jumps + 1;
        }
        return min;
    }

    /**  Greedy approach */
    public int minJumpGreedy(int[] nums) {
        if (nums[0] == 0 && nums.length > 1)
            return Integer.MAX_VALUE;

        // maxPos = maximum that one can reach,
        // pos is pointer to keep track of "position that can be reached used the current jumpCount"
        int pos = 0, maxPos = 0;
        int jumpCount = 0;

        for(int i = 0; i < nums.length-1; i++){
            maxPos = Math.max(maxPos, i+nums[i]);
            if(pos == i){
                pos = maxPos;
                jumpCount++;
            }
        }
        return jumpCount;
    }
}