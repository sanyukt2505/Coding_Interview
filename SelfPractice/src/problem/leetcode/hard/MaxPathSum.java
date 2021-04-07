package problem.leetcode.hard;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * Solution description: https://discuss.leetcode.com/topic/4407/accepted-short-solution-in-java
 *  A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
 *  A recursive method maxPathDown(Node node):
 *      (1) computes the maximum path sum with highest node is the input node, update maximum if necessary
 *      (2) returns the maximum sum of the path that can be extended to input node's parent.
 */
public class MaxPathSum {
    static int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    public int maxPathDown(TreeNode root) {
        if (root == null)
            return 0;
        int left = Math.max(0, maxPathDown(root.left));
        int right = Math.max(0, maxPathDown(root.right));
        /**
         * Each node actually has two roles when it comes to function maxPathDown. When processing the final result maxValue,
         * the node is treated as the highest point of a path.
         * When calculating its return value, it is only part of a path (left or right part), and this return value will
         * be used to calculate path sum of other paths with some other nodes(above the current one) as their highest point.
         */
        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
