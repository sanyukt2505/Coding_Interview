package problem.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Given a collection of intervals, merge all overlapping intervals. Intervals need not be in sorted order.
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 */
public class MergeIntervalsSorted_56 {
    public class IntervalComp implements Comparator<int[]> {
        @Override
        public int compare(int[] a, int[] b) {
            return a[0] > b[0]? 1: a[0] == b[0]? 0: -1;
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComp());
     //   Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        LinkedList<int[]> result = new LinkedList();
        for(int[] interval: intervals) {
            if (result.isEmpty() || result.getLast()[1] < interval[0]) {
                result.add(interval);
            } else {
                result.getLast()[1] = Math.max(result.getLast()[1], interval[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
