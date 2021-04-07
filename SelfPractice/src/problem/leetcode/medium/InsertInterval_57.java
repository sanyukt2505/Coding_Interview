package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/submissions/
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 */
public class InsertInterval_57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        /** Add all the intervals to one list and sort it
         * Perform Merge Intervals on it */
        List<int[]> intervalList = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++)
            intervalList.add(intervals[i]);

        intervalList.add(newInterval);

        Collections.sort(intervalList, (a, b) -> a[0] - b[0]);

        LinkedList<int[]> result = new LinkedList();
        for(int[] interval: intervalList) {
            if (result.isEmpty() || result.getLast()[1] < interval[0]) {
                result.add(interval);
            } else {
                result.getLast()[1] = Math.max(result.getLast()[1], interval[1]);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
