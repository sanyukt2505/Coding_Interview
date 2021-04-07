package problem.leetcode.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** https://leetcode.com/problems/longest-consecutive-sequence/
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]          Output: 9
 */
public class LongestConsecutiveSubsequence_128 {
    /** T: O(n log n)  as sorting is performed  */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        /** sort the array */
        Arrays.sort(nums);
        int longest = 1;
        int currLongest = 1;

        for (int i = 0; i < nums.length-1; i++) {
            /** skipping the duplicates */
            if (nums[i] != nums[i+1]) {
                /**  check if two numbers are consecuiteve */
                if (nums[i] == nums[i+1] - 1) {
                    currLongest++;
                } else {
                    longest = Math.max(currLongest, longest);
                    currLongest = 1;
                }
            }
        }
        return Math.max(currLongest, longest);
    }

    /**  T: O(n)  using HashSet  */
    public int longestConsecutiveOn(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            /** checking if this is a start of a subSequence */
            if (!num_set.contains(num-1)) {
                int currentNum = num;
                int currentStreak = 1;

                /** keep looking for the next number in sequence*/
                while (num_set.contains(currentNum+1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
