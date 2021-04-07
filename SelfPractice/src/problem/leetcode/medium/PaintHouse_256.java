package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/paint-house/solution/
 *
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green.
 * The cost of painting each house with a certain color is different. You have to paint all the houses
 * such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with the color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
 *
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 */
public class PaintHouse_256 {
    /**
     * If first house if painted by color p1, the 2nd house can only be painted by one of the cheapest color except p1
     * of you choose p2 for the seconf house, then 3rd house can be painted by cheapest color except p2
     */
    public int minCost(int[][] costs) {
        int i;

        for (i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
        }
        return Math.min(Math.min(costs[i-1][0], costs[i-1][1]), costs[i-1][2]);
    }

    /**
     * Recursive approach       Time: O(2^n)    Space: O(n)
     * With Memoization         Tine: O(n)      space: O(n)
     */
    public static int minCostRecursive(int[][] costs) {
        int minCost = Integer.MAX_VALUE;
        Map<String, Integer> mem = new HashMap<>();

        /** creating all the permutations */
        for (int i = 0; i < costs[0].length; i++) {
            minCost = Math.min(minCost, paint(0, i, costs, mem));
        }
        return minCost;
    }

    /** If you chose a color for house 1, 2nd house will be painted with a cheaper color of the other 2
     *  total cost od a house = cost of painting that house a particular color and painting ones after it optimally. */
    public static int paint(int house, int color, int [][] costs, Map<String, Integer> mem) {
        if (mem.containsKey(house + " "+ color)) {
            return mem.get(house + " "+ color);
        }

        int totalCost = costs[house][color];
        if (house == costs.length - 1) {

        } else if (color == 0) {  // red
            totalCost += Math.min(paint(house + 1, 1, costs, mem), paint(house + 1, 2, costs, mem));
        } else if (color == 1) {    // green
            totalCost += Math.min(paint(house + 1, 0, costs, mem), paint(house + 1, 2, costs, mem));
        } else {   // blue
            totalCost += Math.min(paint(house + 1, 0, costs, mem), paint(house + 1, 1, costs, mem));
        }
        mem.put(house + " "+ color, totalCost);
        return totalCost;
    }

    public static void main(String[] args) {
        System.out.println(minCostRecursive(new int[][] {{1,2,17},{1,2,17},{16,16,5},{14,3,19}}));
    }
}
