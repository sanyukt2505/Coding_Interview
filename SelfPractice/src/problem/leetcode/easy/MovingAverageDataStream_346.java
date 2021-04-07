package problem.leetcode.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageDataStream_346 {
    int size;
    int sum = 0;
    int count = 0;
    Deque q = new ArrayDeque();

    /** Use a Double-ended Queue */
    public MovingAverageDataStream_346(int size) {
        this.size = size;
    }

    public double next(int val) {
        ++count;
        q.add(val);

        int tail = count > size ? (int)q.poll() : 0;
        sum = sum - tail + val;
        return sum * 1.0 / Math.min(count, size);
    }
}