package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/tweet-counts-per-frequency/
 * Count tweets per time frequency
 *  * Your TweetCounts object will be instantiated and called as such:
 *  * TweetCounts obj = new TweetCounts();
 *  * obj.recordTweet(tweetName,time);
 *  * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */
public class TweetsCountPerFrequency_1348 {
    HashMap<String, List<Integer>> map;
    public TweetsCountPerFrequency_1348() {
        map = new HashMap<String, List<Integer>>();
    }

    public void recordTweet(String tweetName, int time) {
        if (!map.containsKey(tweetName)) {
            List<Integer> list = new ArrayList<>();
            map.put(tweetName, list);
        }
        map.get(tweetName).add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int interval = 60;
        if (freq.equals("hour"))
            interval *= 60;
        else if (freq.equals("day"))
            interval *= 60 * 24;

        List<Integer> res = new ArrayList<>();

        /** get the number of possible intervals,
        // if startTime = 30 and endTime = 150 with minute as freq
        // (150 - 30) / 60 = 2, this means there will be 3 intervals
        // [30, 90); [90, 150); [150, 150) */
        for (int i = 0; i <= (endTime-startTime) / interval; i++)
            res.add(0);

        for (int time: map.get(tweetName)) {
            if (time >= startTime && time <= endTime) {
                int index = (time - startTime)/interval;
                res.set(index, res.get(index) + 1);
            }
        }
        return res;
    }
}
