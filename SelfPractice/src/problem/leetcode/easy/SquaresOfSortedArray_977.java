package problem.leetcode.easy;

/**
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 */
public class SquaresOfSortedArray_977 {
    public int[] sortedSquares(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int[] B = new int[A.length];
        int i = right;

        while (left < right) {
            B[i--] = Math.max(A[left] * A[left], A[right] * A[right]);
            if (A[left] * A[left] > A[right] * A[right]) {
                left++;
            } else {
                right--;
            }
        }
        return B;
    }
}
