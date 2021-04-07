package problem.leetcode.easy;

/**
 * https://leetcode.com/problems/move-zeroes/
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class MoveZeroToEndOfArray_283 {
    public void moveZeroes(int[] nums) {
        int firstZero = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[firstZero];
                nums[firstZero++] = temp;
            }
        }
    }
}
