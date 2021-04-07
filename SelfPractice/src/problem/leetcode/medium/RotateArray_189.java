package problem.leetcode.medium;

/**
 * https://leetcode.com/problems/rotate-array/
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 */
public class RotateArray_189 {

    /** Using Cyclic jumps   */
    public void rotateCyclic(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    /**  Using in place reverse */
    public static void rotate(int[] nums, int k) {
        if(k < 0){
            if (Math.abs(k) > nums.length) {
                k = -(Math.abs(k) % nums.length);
            }
            k = (nums.length + k ) % nums.length; //make it same as right shift
        } else {
            k = k % nums.length;
        }
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length-1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, -3);

        for (int num : nums) {
            System.out.println(num);
        }
    }
}
