package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/contiguous-array/solution/
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * Input: [0,1,1,1,0,0,1,1,0]
 * Output: 6
 *
 * We make an entry for a (count, index) in the Map whenever the count is encountered first,
 * and later on use the corresponding index to find the length of the largest subarray with equal no. of zeros and ones
 * when the same count is encountered again.
 */
public class ContiguousArray_525 {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            // Array from index 0 to i contains equal number of 0's and 1's
            if(count == 0)
                maxlen = Math.max(maxlen, i+1);
            /** if a count is already reached before and now,
            // means that num of 0 = num of 1, between these 2 occurences  */
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }
}
