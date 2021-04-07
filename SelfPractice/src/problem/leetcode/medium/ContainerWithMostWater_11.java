package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class ContainerWithMostWater_11 {

    public int maxArea(int[] height) {
        int low = 0;
        int high = height.length - 1;
        int maxWater = Integer.MIN_VALUE;

        while(low < high) {
            maxWater = Math.max(maxWater, Math.min(height[low], height[high]) * (high - low));
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return maxWater;
    }
}
