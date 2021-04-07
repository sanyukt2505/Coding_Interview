package problem.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *     1            <---
 *   /  \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *    \
 *     6
 * Output: [1, 3, 4, 6]
 *
 * Soln:
 * BFS using a Queue and print the last element in each iteration
 */
public class BinaryTreeRightSideView_199 {
    public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (i == size-1) {
                    result.add(curr.val);
                }

                if(curr.left != null) {
                    q.add(curr.left);
                }

                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
        return result;
    }
}
