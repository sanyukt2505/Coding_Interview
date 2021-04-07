package com.leetcode.arrays.strings;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FindKthLargest {
    /**
     * O(N lg N) running time + O(1) memory
     */
    public static int findKthLargestBruteForce(int[] nums, int k) {
        final int N = nums.length;
        Arrays.sort(nums);
        return nums[N - k];
    }

    /**
     * O(N lg K) running time + O(K) memory
     */
    public static int findKthLargestPriorityQueue(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] numsOne = {3, 2, 1, 5, 6, 4};

        System.out.println("Second largest using Brute Force: " + findKthLargestBruteForce(numsOne, 2));

        int[] numsTwo = {3, 2, 1, 5, 6, 4};
        System.out.println("Second largest using Priority Queue: " + findKthLargestPriorityQueue(numsTwo, 2));
    }
}
