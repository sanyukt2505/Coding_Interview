package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once. Find this single element that appears only once.
 * Follow up: Your solution should run in O(log n) time and O(1) space.
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 */
public class SingleElementSortedArray_540 {

    /** Linear search O(n) */
    public int singleNonDuplicateOn(int[] nums) {
        for (int i = 0; i < nums.length - 1; i+=2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /** Binary Search O(log n)
     * The array containing the single element is always odd-lengthed
     * You find the mid and look for the matching partner
     *   - Case 1: Mid’s partner is to the right, and the halves were originally even.
     *         that means the single element in in right side, set lo to mid + 2
     *   - Case 2: Mid’s partner is to the right, and the halves were originally odd.
     *          single element is on left side. We need to set hi to mid - 1
     *   - Case 3 and 4 are vice versa or above     */
    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean halvesAreEven = (hi - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }
}
