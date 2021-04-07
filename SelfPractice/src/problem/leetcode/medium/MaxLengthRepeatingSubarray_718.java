package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * A: [1,2,3,2,1]   B: [3,2,1,4,7]      Output: 3
 * The repeated subarray with maximum length is [3, 2, 1].
 */
public class MaxLengthRepeatingSubarray_718 {
    // the problem is similar to LongestCommonSubstring
    public int findLength(int[] A, int[] B) {
        int[][] temp = new int[A.length + 1][B.length + 1];
        int maxLen = 0;

        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i-1] == B[j-1]) {
                    temp[i][j] = temp[i-1][j-1] + 1;

                    if (temp[i][j] > maxLen) {
                        maxLen = temp[i][j];
                    }
                }
            }
        }
        return maxLen;
    }
}
