package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
 * There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 */
public class MaxPointsFromKCards_1423 {
    /**
     * This is a sliding window problem, with fixed k-length window size and k moves.
     * The only relevant range is the first and the last k elements.
     * Every step we eliminate one most-right element from the first half,
     * and add one element towards left to the second half, then update the maxScore variable.
     */
    public int maxScore(int[] cardPoints, int k) {
        int globalMax = 0;

        /**  calculate sum of left k elements*/
        for(int i = 0; i < k; i++){
            globalMax += cardPoints[i];
        }

        int tempValue = globalMax;
        int left = k - 1;                       // index of element to remove
        int right = cardPoints.length - 1;      // index of element to include

        while(k > 0){
            tempValue -= cardPoints[left];
            tempValue += cardPoints[right];
            left--;
            right--;
            globalMax = Math.max(globalMax, tempValue);
            k--;
        }
        return globalMax;
    }
}
