package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/wiggle-sort/
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]...
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 *
 * Solution: for even index check a[i] < a[i+1] and or odd check a[i] > a[i+1]
 *              if not, swap
 */
public class WiggleSort_280_384 {
    public void wiggleSort(int[] nums) {
        boolean isLess = true;

        for (int i = 0; i < nums.length - 1; i++) {
            if (isLess) {
                if (nums[i] > nums [i+1]) {
                    int tmp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = tmp;
                }
            } else {
                if (nums[i] < nums [i+1]) {
                    int tmp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = tmp;
                }
            }
            isLess = !isLess;
        }
    }
}
