package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/
 * Given a collection of distinct integers, return all possible permutations.
 * Input: [1,1,2]
 * Output: [[1,1,2],[1,2,1],[2,1,1]]
 *
 * Solution:
 * Backtracking: 1. Choose an element   2. Explore the option   3. Un-chose the element
 */
public class PermutationsUnique {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 1)
            return res;

        Arrays.sort(nums);
        backtrack(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> curr, int[] nums, boolean[] visited) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i])
                continue;

            if (i > 0 && nums[i] == nums[i-1] && !visited[i - 1])
                continue;

            curr.add(nums[i]);
            visited[i] = true;
            backtrack(res, curr, nums, visited);
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }
}
