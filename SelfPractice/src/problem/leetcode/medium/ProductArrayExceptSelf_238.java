package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/product-of-array-except-self/
 * Given an array nums of n integers where n > 1,
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i]
 * Note: Cannot use Division
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 */
public class ProductArrayExceptSelf_238 {
    /**
     * Make use of the product of all the numbers to the left and all the numbers to the right of the index.
     * Multiplying these two individual products would give us the desired result as well
     * T: O(n)      S: O(n)
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];

        /** for a given index i, Left[i] would contain the product of all the numbers to the left of i
        // and Right[i] would contain the product of all the numbers to the right of i   */
        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = 1;
        for(int i=1; i<len; i++) {
            left[i] = nums[i-1] * left[i-1];
        }

        right[len-1] = 1;
        for(int i=len-2; i >= 0; i--) {
            right[i] = nums[i+1] * right[i+1];
        }

        for (int i=0; i<len; i++)
            ans[i] = left[i] * right[i];

        return ans;
    }
}
