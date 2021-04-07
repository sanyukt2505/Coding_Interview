package com.leetcode.arrays.strings;

/**
 * LeetCode problem: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * Solution is based upon: https://discuss.leetcode.com/topic/4100/compact-and-clean-c-solution
 */
public class MinimumInRotatedSortedArray {

    /**
     * Classic binary search problem.
     * Looking at sub-array with index [start,end]. We can find out that if the first member is less than the last member, there's no rotation in the array.
     * So we could directly return the first element in this sub-array.
     *
     * If the first element is larger than the last one, then we compute the element in the middle, and compare it with the first element.
     * If value of the element in the middle is larger than the first element, we know the rotation is at the second half of this array.
     * Else, it is in the first half in the array.
     * @param arr
     * @return
     */
    private static int findMin(int[] arr) {
        int start = 0, end = arr.length - 1;

        while (start < end) {
            if (arr[start] < arr[end]) {
                return arr[start];
            }

            int mid = start + (end - start) / 2;

            if (arr[mid] >= arr[start]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return arr[start];
    }

    public static void main(String[] args) {
        int[] arr = {4,5,6,2,3};
        System.out.println("Minimum: " + findMin(arr));
    }
}
