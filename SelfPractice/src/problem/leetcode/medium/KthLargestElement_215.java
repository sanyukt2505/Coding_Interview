package problem.leetcode.medium;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Soln:
 * Use a minHeap of size K and keep adding elements to it until it is of size k
 * At this moment the minimum will be at t queue[0]
 * Now add element only if they are greater than queue[0].
 * Once you are done with all elements the MinHeap will have all K largest element with the kth largest at queue[0]
 */
public class KthLargestElement_215 {
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> q = new PriorityQueue(k);

        for (int i=0; i < nums.length; i++) {
            if (i < k ) {
                q.add(nums[i]);
            } else if (nums[i] > q.peek()) {
                q.poll();
                q.add(nums[i]);
            }
        }
        return q.poll();
    }
}
