package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 * Input: arr = [5,5,4], k = 1              Output: 1   Explanation: Remove the single 4, only 5 is left.
 * Input: arr = [4,3,1,1,3,3,2], k = 3      Output: 2
 */
public class LeastNumUniqueIntgerAfterKRemoval_1481 {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> occrCount = new HashMap<>();

        // creating a map of number and occurenceCount
        for (int num: arr)
            occrCount.put(num, occrCount.getOrDefault(num, 0) + 1);

        // create a minHeap and add all the values from Map to it
        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((a, b)-> a.getValue() - b.getValue());
        heap.addAll(occrCount.entrySet());

        // remove the element with least occurence -- k times
        // for each iteration, add the Entry back to heap if its values if more than 1
        while (k > 0) {
            Map.Entry<Integer, Integer> min = heap.remove();
            k--;
            min.setValue(min.getValue() - 1);
            if (min.getValue() > 0) {
                heap.add(min);
            }
        }
        return heap.size();
    }
}
