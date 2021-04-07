package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array
 *
 * Formula: If a sorted array is shifted, if you take the middle, always one side will be sorted. Take the recursion according to that rule.
 *
 * 1- take the middle and compare with target, if matches return.
 * 2- if middle is bigger than left side, it means left is sorted
 *  2a- if [left] < target < [middle] then do recursion with left, middle - 1 (right)
 *  2b- left side is sorted, but target not in here, search on right side middle + 1 (left), right
 * 3- if middle is less than right side, it means right is sorted
 *  3a- if [middle] < target < [right] then do recursion with middle + 1 (left), right
 *  3b- right side is sorted, but target not in here, search on left side left, middle -1 (right)
 */
public class SortedRotatedArraySearch_33 {

    public static int searchRecursive(int[] nums, int target) {
        int end = nums.length;
        int value = search(nums, 0, end-1, target);
        return value;
    }

    public static int search(int[] nums, int start, int end, int target) {
        if (start > end)
            return -1;
        int mid = (end+start) / 2;
        if (nums[mid] == target)
            return mid;

        if (nums[mid] >= nums[start]) {
            if (target >= nums[start] && target < nums[mid])
                end = mid - 1;
            else
                start = mid + 1;
        } else {
            if (target > nums[mid] && target <= nums[end])
                start = mid + 1;
            else
                end = mid -1;
        }
        return search(nums, start, end, target);
    }

    public static int searchIterative(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            else {
                if (target <= nums[end] && target > nums[mid])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4,5,6,7,0,1,2};
        System.out.println(searchRecursive(nums, 0));
        System.out.println(searchIterative(nums, 1));
    }
}
