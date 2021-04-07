package problem.leetcode.medium;

import java.util.List;

/**
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it.
 * If such index doesn't exist, return -1.
 * Input: mat = [[0,0],
 *              [1,1]]      Output: 0
 * Input: mat = [[0,0,0,1],
 *               [0,0,1,1],
 *               [0,1,1,1]]     Output: 1
 */
public class LeftmostColWithaOneBinaryArray_1428 {
    interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int row = binaryMatrix.dimensions().get(0);
        int col = binaryMatrix.dimensions().get(1);
        int res = col;

        /** perform binary search on each row to find the first instance of 1*/
        for (int r = 0; r < row; r++) {
            int low = 0;
            int high = col - 1;

            while (low < high) {
                int mid = (low + high)/2;
                int curr = binaryMatrix.get(r, mid);
                if (curr == 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            /** If the last element in the search space is a 1, then this row contained a 1. */
            if (binaryMatrix.get(r, low) == 1) {
                res = Math.min(res, low);
            }
        }
        /** we have set res = right most index, if it is still the same that means no 1 was found */
        return res == col ? -1 : res;
    }
}
