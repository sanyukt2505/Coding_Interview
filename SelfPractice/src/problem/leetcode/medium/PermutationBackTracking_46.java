package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 * https://www.youtube.com/watch?v=RkXl5iYoQn4
 * Given a collection of distinct integers, return all possible permutations.
 * Input: [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class PermutationBackTracking_46 {
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 1)
            return res;

        backtrack1(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return res;
    }

    /**
     * Solution:
     * Backtracking: 1. Choose an element   2. Explore the option   3. Un-chose the element
     */

    public void backtrack1(List<List<Integer>> res, List<Integer> curr, int[] nums, boolean[] visited) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i])
                continue;
            // add one of the number that is not yet visited to the list   -- choose
            curr.add(nums[i]);
            visited[i] = true;
            backtrack1(res, curr, nums, visited);                           // explore
            // after backtracking remove the added element form the list    // un-choose
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }

    }

    /**
     *  Here is a backtrack function which takes the index of the first integer to consider as an argument backtrack(first).
     *
     *  If the first integer to consider has index n that means that the current permutation is done.
     *  Iterate over the integers from index first to index n - 1.
     *  Place i-th integer first in the permutation, i.e. swap(nums[first], nums[i]).
     *  Proceed to create all permutations which starts from i-th integer : backtrack(first + 1).
     *  Now backtrack, i.e. swap(nums[first], nums[i]) back.
     */

    public static void backtrack(int n, ArrayList<Integer> nums, List<List<Integer>> output, int first) {
        // if all integers are used up
        if (first == n)
            output.add(new ArrayList<Integer>(nums));
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList<>();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }

    public static void main(String[] args) {
        for(List list: permute(new int[]{1,2,3})) {
            for (int i=0; i<list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }
}
