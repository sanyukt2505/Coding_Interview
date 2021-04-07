package problem.leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 * Given a sorted array arr, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * Input: arr = [1,2,3,4,5], k = 4, x = 3     Output: [1,2,3,4]
 * Input: arr = [1,2,3,4,5], k = 4, x = 3  Output: [1,2,3,4]
 * Input: arr = [11,22,23,24,65], k = 4, x = 20  Output: [11,22,23,24]
 *
 * The idea is to perform binary search over the array and find the element if it exists, otherwise we find the
 * closest element and we return its index. once we have the index we'll have two pointers to look for closest elements
 * both left and right
 */
public class KClosestElements_658 {
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (arr == null || arr.length == 0)
            return ans;

        int index = Arrays.binarySearch(arr, x);
        int left = -1, right = -1;
        if (index < 0) {
            index = Math.abs(index) - 1;
            right = index;
        } else {
            ans.add(arr[index]);
            k--;
            right = index + 1;
        }
        left = index - 1;

        while(k > 0) {
            int leftDiff = left< 0 ? Integer.MAX_VALUE : Math.abs(x - arr[left]);
            int rightDiff = right > arr.length - 1 ? Integer.MAX_VALUE : Math.abs(x - arr[right]);

            /* to keep the list sorted, if we find closest on the left we add it to front */
            if (leftDiff <= rightDiff) {
                ans.addFirst(arr[left--]);
            } else {
                /* if the closest is on the right we add it to the end */
                ans.add(arr[right++]);
            }
            k--;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[] {11,22,23,24,65}, 4, 20));
    }
}
