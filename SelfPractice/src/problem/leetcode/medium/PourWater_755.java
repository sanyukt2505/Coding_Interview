package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/pour-water/
 * Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
 * Output: [2,2,2,3,2,2,2]
 * Explanation:
 * #               #
 * #       W       #
 * # # W W # W # # #
 * # # # # # # # # #
 *   0 1 2 3 4 5 6    <- index
 */
public class PourWater_755 {
    public int[] pourWater(int[] heights, int V, int K) {
        for (int i = 0; i < V; i++) {
            int pos = getFirstLowerFromLeft(heights, K);
            if (pos == K) {
                // try move right
                pos = getFirstLowerFromRight(heights, K);
            }
            heights[pos]++;
        }
        return heights;
    }

    private int getFirstLowerFromLeft(int[] heights, int k) {
        int i = k;
        int minIndex = k;
        int minSoFar = heights[k];
        while (i >= 0) {
            if (heights[i] < minSoFar) {
                minSoFar = heights[i];
                minIndex = i;
            } else if (i >= 0 && i < k && heights[i + 1] < heights[i]) {
                break;
            }
            i--;
        }
        return minIndex;
    }

    private int getFirstLowerFromRight(int[] heights, int k) {
        int i = k;
        int minIndex = k;
        int minSoFar = heights[k];
        while (i < heights.length) {
            if (heights[i] < minSoFar) {
                minSoFar = heights[i];
                minIndex = i;
            } else if (i > k && i < heights.length && heights[i] > heights[i - 1]) {
                break;
            }
            i++;
        }
        return minIndex;
    }
}
