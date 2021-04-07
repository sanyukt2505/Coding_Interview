package problem.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * binary tree is a tree data structure in which each node has at most two children,
 * which are referred to as the left child and the right child
 */
public class BinaryTree {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void levelOrder(Node root) {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                Node curr = queue.remove();
                System.out.print(curr.data + " ");
                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
                size--;
            }
            System.out.println();
        }
    }

    /**
     * Given a binary tree, the task is to flip the binary tree towards right direction that is clockwise
     * left most node becomes the root of flipped tree and its parent become its right child and the right sibling become its left child
     * and same should be done for all left most nodes recursively.
     *     root->left->left = root->right;
     *     root->left->right = root;
     *     root->left = NULL;
     *     root->right = NULL;
     * https://www.geeksforgeeks.org/flip-binary-tree/
     */
    public static Node flipBinaryTreeRecursive(Node root) {
        if (root == null)
            return null;

        if (root.left == null && root.right == null)
            return root;

        // find the root after flip
        Node flipRoot = flipBinaryTreeRecursive(root.left);

        // rearranging main root Node after returnin from recursive call
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return flipRoot;
    }

    /**
     * flip the binary tree towards right direction that is clockwise
     * Think recursively in bottom-up fashion.
     * If we reassign the bottom-level nodes before the upper ones, we won't have to make copies and worry about overwriting something.
     * We know that the new root would be the left-most leaf node, so we begin reassignment here.
     */
    public static Node dfsBottomUp(Node p, Node parent) {
        if (p == null) {
            return parent;
        }

        Node root = dfsBottomUp(p.left, p);
        root.left = p.right;
        root.right = p;

        return root;
    }

    public static Node flipBinaryTreeIterative(Node root) {
        Node curr = root;
        Node next = null, prev = null, temp = null;

        while (curr != null) {
            next = curr.left;

            // Swapping nodes now, need temp to keep the previous right child
            // Making prev's right as curr's left child
            curr.left = temp;

            // Storing curr's right child
            temp = curr.right;

            // Making prev as curr's right child
            curr.right = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * Invert a binary tree (mirror a tree)
     * https://leetcode.com/problems/invert-binary-tree/
     *
     * Recursive approach
     *      Call invert for left-subtree.
     *      Call invert for right-subtree.
     *      Swap left and right subtrees.
     */
    public static Node invertTreeRecursive(Node root) {
        if(root == null) {
            return null;
        }

        Node left = invertTreeRecursive(root.left);
        Node right = invertTreeRecursive(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static Node invertTreeIterative(Node root) {
        if(root == null) {
            return null;
        }
        Queue<Node> q = new LinkedList<Node> ();
        q.add(root);

        while(!q.isEmpty()) {
            // using poll() cause poll does not throw an error if the queue is empty, but remove() does
            Node curr = q.poll();
            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if (curr.left != null) {
                q.add(curr.left);
            }

            if (curr.right != null) {
                q.add(curr.right);
            }
        }
        return root;
    }

    /**
     * Solution to the Leetcode problem: https://leetcode.com/problems/same-tree/
     * Use of Depth First Search algorithm (special Pre-order traversal)
     * @param p Root Node of the first tree
     * @param q Root Node of the second tree
     * @return if trees are same
     */
    public boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.data == q.data) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        return false;
    }

    /**
     * Solution to the problem: Lowest Common Ancestor of a Binary Search Tree (https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
     * Approach: Walk down from whole tree's root as long as both p and q are in the same subtree
     *          (meaning their values are both smaller and larger than root's value).
     *          This walks straight from root to LCA, not looking at the rest of the tree
     * @param root
     * @param p
     * @param q
     * @return Node that is Lowest common ancestor of
     */
    public Node lowestCommonAncestorBST(Node root, Node p, Node q) {
        while ((root.data - p.data) * (root.data - q.data) > 0) {
            // Notice: The root value changes to
            //  1.  root.left IF p.left.val < root.val
            //  2.  root.right IF p.left.val > root.val
            root = p.data < root.data ? root.left : root.right;
        }
        return root;
    }

    /**
     * Solution to the problem: Lowest Common Ancestor of a Binary Search Tree (https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
     * Solution discussion: https://discuss.leetcode.com/topic/18566/my-java-solution-which-is-easy-to-understand
     *
     * The idea is to traverse the tree starting from root.
     * If any of the given keys (n1 and n2) matches with root, then root is LCA (assuming that both keys are present).
     * If root doesn’t match with any of the keys, we recur for left and right subtree.
     * The node which has one key present in its left subtree and the other key present in right subtree is the LCA.
     * If both keys lie in left subtree, then left subtree has LCA also, otherwise LCA lies in right subtree.
     */
    public static Node lowestCommonAncestorBinaryTree(Node root, Node p, Node q) {
        if(root == null) return null;
        if(root == p || root == q)  return root;

        Node left = lowestCommonAncestorBinaryTree(root.left, p, q);
        Node right = lowestCommonAncestorBinaryTree(root.right, p, q);

        if(left != null && right != null) return root;
        if(left == null && right == null) return null;
        return left != null ? left : right;
    }

    /**  Returns level of key k if it is present in tree, otherwise returns -1  */
    public static int findLevel(Node root, int a, int level)
    {
        if (root == null)
            return -1;
        if (root.data == a)
            return level;
        int left = findLevel(root.left, a, level + 1);
        if (left == -1)
            return findLevel(root.right, a, level + 1);
        return left;
    }

    /**
     * Find distance between two nodes of a Binary Tree
     * https://www.geeksforgeeks.org/find-distance-between-two-nodes-of-a-binary-tree/
     */
    public static int findDistance(Node root, Node a, Node b)
    {
        Node lca = lowestCommonAncestorBinaryTree(root, a, b);
//        commented for compilation issue
//        int d1 = findLevel(lca, a, 0);
//        int d2 = findLevel(lca, b, 0);
//        return d1 + d2;
        return 1;
    }

    /**
     * Solution to the Unique Binary Trees count problem: https://leetcode.com/problems/unique-binary-search-trees/
     * Solution description: https://discuss.leetcode.com/topic/8398/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i
     */
    public static int numTrees(int n) {
        int[] G = new int[n+1];
        G[0] = G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1] * G[i-j];
            }
        }
        return G[n];
    }

    /**
     * Recursive solution to the 'is tree symmetric' problem: https://leetcode.com/problems/symmetric-tree/
     */
    public boolean isSymmetricRecursive(Node root) {
        // If root is empty: tree is symmetric
        if (root == null) return true;

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return (p.data == q.data) && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    /**
     * Iterative solution to the 'is tree symmetric' problem: https://leetcode.com/problems/symmetric-tree/
     * Solution link: https://discuss.leetcode.com/topic/5941/recursive-and-non-recursive-solutions-in-java/16
     */
    public boolean isSymmetricIterative(Node root) {
        // If root is empty: tree is symmetric
        if (root == null) return true;

        Stack<Node> stack = new Stack<>();
        if (!process(root.left, root.right, stack)) return false;

        while (!stack.isEmpty()) {
            if (stack.size() % 2 != 0) return false;

            Node p = stack.pop();
            Node q = stack.pop();

            if (p.data != q.data) return false;

            if (!process(p.left, q.right, stack)) return false;
            if (!process(p.right, q.left, stack)) return false;
        }

        return true;
    }

    private boolean process(Node a, Node b, Stack<Node> stack) {
        if (a != null) {
            if (b == null) return false;
            stack.push(a);
            stack.push(b);
        } else if (b != null) {
            return false;
        }
        return true;
    }

    /**
     * Solution to the pathsum problem: https://leetcode.com/problems/path-sum/
     */
    public static boolean hasPathSum(Node root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) {
            return  root.data == sum;
        }
        return hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data);
    }

    /**
     * Solution to the maximum path sum problem: https://leetcode.com/problems/binary-tree-maximum-path-sum/
     * Solution description: https://discuss.leetcode.com/topic/4407/accepted-short-solution-in-java
     *  A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
     *  A recursive method maxPathDown(Node node):
     *      (1) computes the maximum path sum with highest node is the input node, update maximum if necessary
     *      (2) returns the maximum sum of the path that can be extended to input node's parent.
     */
    static int maxValue;

    public static int maxPathSum(Node root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private static int maxPathDown(Node node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.data);
        return Math.max(left, right) + node.data;
    }

    public static void main(String[] args) {
        Node root=new Node(1);
        root.left=new Node(2);
        root.right=new Node(3);
        root.right.left = new Node(34);
        root.right.right = new Node(35);
        System.out.println("Level order traversal of given tree");
        levelOrder(root);

        // ---------- invert tree  --------------
        Node root1 = invertTreeRecursive(root);
        System.out.println("Level order traversal after invert recursive");
        levelOrder(root1);
        root1 = invertTreeIterative(root);
        System.out.println("Level order traversal after invert iterative");
        levelOrder(root1);

//        // ----------  flip tree  -------------
//        Node root1 = flipBinaryTreeRecursive(root);
//        System.out.println("Level order traversal of flipped tree - Recursive");
//        levelOrder(root1);
//
//        Node root2=new Node(1);
//        root2.left=new Node(2);
//        root2.right=new Node(3);
//        root2.right.left = new Node(34);
//        root2.right.right = new Node(35);
//        System.out.println("Level order traversal of given tree");
//        levelOrder(root2);
//
//        root1 = flipBinaryTreeIterative(root2);
//        System.out.println("Level order traversal of flipped tree - Iterative");
//        levelOrder(root1);
    }
}
