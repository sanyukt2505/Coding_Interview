package problem.Design;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Implement a RateLimiter Class with an isAllow method. Every request comes in with a unique clientID,
 * deny a request if that client has made more than 100 requests in the past second.
 */
public class RateLimiter {
    public int REQUEST_LIMIT = 100;
    public Long TIME_LIMIT = 1000L;

    public Hashtable<String, Queue<Long>> clientTimeStampMap = new Hashtable<>();

    public boolean isAllow(String clientId) {
        long currTime = System.currentTimeMillis();
        if (!clientTimeStampMap.containsKey(clientId)) {
            Queue<Long> queue = new LinkedList<>();
            queue.add(currTime);
            return true;
        } else {
            Queue<Long> queue = clientTimeStampMap.get(clientId);
            /* when a timestamp hit, we should poll all the timestamp before TIME_LIMIT*/
            while (!queue.isEmpty() && (currTime - queue.peek() >= TIME_LIMIT))
                queue.poll();

            if (queue.size() < REQUEST_LIMIT) {
                queue.add(currTime);
                return true;
            }
            return false;
        }
    }

    /**
     * Using 60 Buckets approach : one bucket per second
     */
    private static class Bucket {
        //store the actual epoch time for this bucket
        //we only care about seconds granularity
        Long secondsEpoch = -1L;
        //a map of clientIds to the number of requests made for this second
        Map<String, Integer> requestCounts = new HashMap<>();
    }

    private Bucket[] seconds = new Bucket[60];

    public RateLimiter() {
        for (int i = 0 ; i < seconds.length; i++) {
            seconds[i] = new Bucket();
        }
    }

    //synchronize the isAllow for concurrent access
    public synchronized boolean isAllowBuckets(String clientId) {
        long currTime = System.currentTimeMillis();
        long secondsEpoch = currTime / 1000l;
        Long second = secondsEpoch % 60;

        Bucket bucket = seconds[Math.toIntExact(second)];
        //if we have advanced the second then clear the current count
        if (bucket.secondsEpoch != secondsEpoch) {
            bucket.secondsEpoch = secondsEpoch;
            bucket.requestCounts.clear();
        }
        int requestCount = bucket.requestCounts.getOrDefault(clientId, 0);
        if (requestCount > 100) {
            return false;
        }
        bucket.requestCounts.put(clientId, requestCount + 1);
        return true;
    }

}