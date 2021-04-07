package problem.leetcode.medium;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 */
public class PartitionEqualSubsetSum_416 {
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int i: nums) {
            total += i;
        }

        //can't partition if the total sum is odd
        if(total % 2 != 0) {
            return false;
        }
        return canPartition(nums, 0, 0, total, new HashMap<String, Boolean>());
    }

    /**  num - array,  index - curr index,  total = current total Sum     */
    public boolean canPartition(int[] nums, int index, int sum, int total, HashMap<String, Boolean> memMap) {
        String key = index + "" + sum;
        if(memMap.containsKey(key)) {
            return memMap.get(key);
        }
        // exit TRUE when sum of current subset is half of totalSum
        if(sum == total / 2) {
            return true;
        }

        /** exit FALSE if sum of current subset is > half of totalSum
            or all the nums are exhausted  */
        if(sum > total / 2 || index >= nums.length) {
            return false;
        }
        /** either select the next num[index] towards the sum
            or do not select next num[index] towards the sum   */
        boolean found = canPartition(nums, index + 1, sum + nums[index], total, memMap)
                || canPartition(nums, index + 1, sum, total, memMap);

        memMap.put(key, found);
        return found;
    }
}
