package problem.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60
 * Input: [30,20,150,100,40]
 * Output: 3
 */
public class PairOfSongDivisibleBy60_1010 {
    public int numPairsDivisibleBy60(int[] time) {
        int res = 0;

        for (int i = 0; i < time.length; i++) {
            time[i]  %= 60;
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < time.length; i++) {
            int t = time[i];
            if (map.containsKey(60 - t)){
                res += map.get(60 - t);
            }
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        return res;
    }
}
