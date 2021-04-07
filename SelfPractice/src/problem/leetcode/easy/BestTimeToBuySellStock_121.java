package problem.leetcode.easy;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Solution:
 *   - The logic to solve this problem is same as "max subarray problem" using Kadane's Algorithm.
 *   - All the straight forward solution should work, but if the interviewer twists the question slightly by giving the
 *     difference array of prices, Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, you might end up being confused.
 *   - Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, and
 *   find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
 */
public class BestTimeToBuySellStock_121 {
    public static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) return 0;

        int maxCur = 0, maxSoFar = 0;

        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i-1];
            maxCur = Math.max(0, maxCur += diff);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }

        return maxSoFar;
    }

    /**
     * The points of interest are the peaks and valleys in the given graph.
     * We need to find the largest peak following the smallest valley.
     * We can maintain two variables - minprice and maxprofit corresponding to the smallest valley
     * and maximum profit (maximum difference between selling price and minprice) obtained so far respectively.
     */
    public int maxProfitEasy(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        int[] prices = {1, 7, 4, 11};
        int maxProfit = maxProfit(prices);
        System.out.println("Max Profit: " + maxProfit);

        int[] pricesSecondInput = {0, 6, -3, 7};
        int maxProfitSecond = maxProfit(pricesSecondInput);
        System.out.println("Max Profit second: " + maxProfitSecond);
    }
}
