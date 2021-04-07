package problem.leetcode.medium;

public class SplitArrayContiguosSubArrIncreasingSum_1013 {
    /**
     * Split Array in 3 equal parts with same sum
     * Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.
     */
    public boolean canThreePartsEqualSum(int[] array) {
        int totalSum = 0;
        /** step1: we compute the sum of entire array, and divide it by 3 to figure out the sum of each partition */
        for(int element : array)
            totalSum += element;

        if (totalSum % 3 != 0) {
            return false;
        }

        int partSum =  totalSum / 3;
        /// step2: loop and find the partitions
        int multiple = 1, currentSum = 0, count = 0;
        for(int element : array) {
            currentSum += element;
            if(currentSum == multiple * partSum) {
                count++;
                multiple++;
            }
        }
        return count >= 3;
    }

    /**
     * Count of ways to split an Array into three contiguous Subarrays having increasing Sum
     * Input: arr[] = {2, 3, 1, 7}
     * Output: 2
     * Explanation: {{2}, {3, 1}, {7}}, {{2}, {3}, {1, 7}} are the possible splits.
     */
    static int findCountWaysSplit(int arr[])
    {
        int n = arr.length;
        // Create a prefix sums array {2, 5, 6, 13}
        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++)
            prefixSum[i] = prefixSum[i - 1] + arr[i];

        // Creates the suffix sums  {13, 11, 8, 7}
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suffixSum[i] = suffixSum[i + 1] + arr[i];

        int s = 1, e = 1;
        int currSubarraySum = 0, count = 0;

        // Traverse the given array
        while (s < n - 1 && e < n - 1) {

            // Updating curr_subarray_sum until it is less than prefix_sum[s-1]
            while (e < n - 1 && currSubarraySum < prefixSum[s - 1]) {
                currSubarraySum += arr[e++];
            }

            if (currSubarraySum <= suffixSum[e]) {
                count++;
            }

            // Decrease curr_subarray_sum by arr[s]
            currSubarraySum -= arr[s++];
        }
        return count;
    }
}

