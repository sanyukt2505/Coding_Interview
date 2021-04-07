package problem.leetcode.medium;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array
 * that can make triangles if we take them as side lengths of a triangle.
 *
 * Input: [2,2,3,4]     Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 */
public class ValidTriangleNumber_611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int result = 0;

        for (int i = 0; i < len - 2; i++) {
            int k = i + 2;
            for (int j = i+1; j < len-1; j++) {
                // check if sum of two side < third side
                // find the index k till the below condition satisfy.
                // No need to match the numbers after k, as those are greater than k and will not satisfy the equation
                while (k < len && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                // adding all the number between the second edge index to k index,
                // as all those number staisfy the equation
                result += k - j - 1;
            }
        }
        return result;
    }
}
