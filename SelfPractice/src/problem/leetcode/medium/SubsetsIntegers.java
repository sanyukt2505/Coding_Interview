package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Input: [1,2,3]
 * Output: [[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
 *
 * Solution:
 * Backtracking: 1. Choose an element   2. Explore the option   3. Un-chose the element
 */
public class SubsetsIntegers {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 1)
            return res;

        // Arrays
        generateSubsets(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    public void generateSubsets(List<List<Integer>> res, List<Integer> curr, int[] nums, int index) {

        res.add(new ArrayList(curr));

        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);
            generateSubsets(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }

    /**
     * https://leetcode.com/problems/subsets-ii/
     * Given a collection of integers that might contain duplicates, Return unique subsets
     * Input: [1,2,2]
     * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
     */
    public List<List<Integer>> subsetsWithDuplicates(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 1)
            return res;

        Arrays.sort(nums);
        generateSubsetsUnique(res, new ArrayList<Integer>(), nums, 0);
        return res;
    }

    public void generateSubsetsUnique(List<List<Integer>> res, List<Integer> curr, int[] nums, int index) {

        res.add(new ArrayList(curr));

        for (int i = index; i < nums.length; i++) {
            curr.add(nums[i]);
            generateSubsetsUnique(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
            while (i < nums.length - 1 && nums[i] == nums [i + 1])
                i++;
        }
    }
}
