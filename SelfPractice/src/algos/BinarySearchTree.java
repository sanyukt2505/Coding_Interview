package algos;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class BinarySearchTree{
    private static class Node {
        public int data;
        public Node right;
        public Node left;

        public Node(int d) {
            data = d;
        }
    }

    private Node root;

    public Node find(int key) {
        Node curr = root;
        while (curr != null && curr.data != key) {
            if (curr.data > key)
                curr = curr.left;
            else
                curr = curr.right;
        }
        return curr;
    }

    public Node findRecursive(Node root, int key) {
        if (root == null)
            return null;

        if (root.data == key)
            return root;

        if (root.data > key)
            return findRecursive(root.left, key);
        else if (root.data < key)
            return findRecursive(root.right, key);

        return null;
    }

    public void insert(int key) {
        Node newNode = new Node(key);

        if (root == null)
            root = newNode;
        else {
            Node curr = root;
            while (true) {
                Node parent = curr;
                if (key < curr.data) {
                    curr = curr.left;
                    if (curr == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    curr = curr.right;
                    if (curr == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node insertRecursive(Node root, int key) {

        if(root == null){
            return new Node(key);
        }

        if (root.data > key) {
            root.left = insertRecursive(root.left, key);
        } else if (root.data < key) {
            root.right = insertRecursive(root.right, key);
        }
        return root;
    }

    public boolean remove(int key) {
        Node curr = root;
        Node removeNode = remove(key, root);
        if (removeNode != null)
            return true;
        else
            return false;
    }

    /**
     * To deleteRecursive a node we need first search it. Then we need to determine if that node has children or not.
     *
     * If no children - Just deleteRecursive.
     * If a single child - Copy that child to the node.
     * If two children - Determine the next highest element (inorder successor) in the right subtree.
     * Replace the node to be removed with the inorder successor. Delete the inorder successor duplicate.
     */
    public Node remove(int key, Node curr) {
        if (curr == null)
            return curr;

        if (curr.data > key)
            curr.left = remove(key, curr.left);
        else if (curr.data < key)
            curr.right = remove(key, curr.right);
        else if (curr.left != null && curr.right != null) {
            curr.data = findMin(curr.right).data;
            curr.right = remove(curr.data, curr.right);
        } else {
            curr = (curr.left != null ? curr.left : curr.right);
        }
        return curr;
    }

    /** Method to find minimum in a subtree
     */
    public Node findMin(Node curr) {
        if(curr == null)
            return curr;
        else if(curr.left == null)
            return curr.left;
        else
            return findMin(curr.left);
    }

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print (root.data + " ");
            inOrder(root.right);
        }
    }

    /* Take a stack. Push root to it
     * keep pushing the node = left child, until it is null
     * when the left == null, then pop and print the element, and push the right child to stack
     */
    public void inOrderIterative(Node root) {
        System.out.print("inOrderIterative ");
        if (root == null)
            return;

        Stack<Node> stack = new Stack();
        Node node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.data + " ");
                node = node.right;
            }
        }
        System.out.println();
    }

    public void preOrder(Node root) {
        if (root != null) {
            System.out.print (root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /* Take a stack. Push root to it
     * Until the stack is empty, pop the element, push the right and push the left
     */
    public void preOrderIterative(Node root) {
        System.out.print("preOrderIterative ");
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print (root.data + " ");
        }
    }

    /* Take a stack add root and all the left child to it until left child == null
     * until stack size != 0, peek the top element
     *      if peek has right child and teh right is not in visitedSet, do the above for the right child
     *      if no right child, print the element, add it to the visitedSet
     */
    public void postOrderIterative(Node root) {
        System.out.print("postOrderIterative ");
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Set<Node> visitedSet = new HashSet<>();
        add(stack, root);

        while (stack.size() > 0) {
            Node node = stack.peek();
            if (node.right != null && !visitedSet.contains(node.right)) {
                add(stack, node.right);
            } else {
                node = stack.pop();
                System.out.print(node.data + " ");
                visitedSet.add(node);
            }

        }
    }

    public void add(Stack stack, Node node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /* Take a queue and add root to it
     * Remove the element from queue, add left child it to queue, add right child to queue
     */
    public void levelOrder(Node root) {
        System.out.print("levelOrder ");
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList();
        q.add(root);

        while(!q.isEmpty()) {
            Node curr = q.remove();
            if (curr != null) {
                System.out.print(curr.data + " ");
                q.add(curr.left);
                q.add(curr.right);
            }
        }
    }

    public int maxDepth(Node root) {
        if (root == null)
            return 0;
        else
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int minDepth(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        if (root.left == null)
            return minDepth(root.right) + 1;
        if (root.right == null)
            return minDepth(root.left) + 1;

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /** Find Deepest leaf Node
     */
    static int maxLevel = -1;
    static int res = -1;

    // maxLevel : keeps track of maximum level seen so far.
    // res : Value of deepest node so far.
    // level : Level of root
    static void find(Node root, int level)
    {
        if (root != null)
        {
            find(root.left, ++level);
            // Update level and resue
            if (level > maxLevel)
            {
                res = root.data;
                maxLevel = level;
            }
            find(root.right, level);
        }
    }

    /** Returns value of deepest node    */
    static int deepestLeafNode(Node root)
    {   // Initialze result and max level  int res = -1;  int maxLevel = -1; */
        // Updates value "res" and "maxLevel"
        // Note that res and maxLen are passed by reference.
        find(root, 0);
        return res;
    }

    /** A utility function to  find deepest leaf node.
        lvl: level of current node.
        isLeft: A bool indicate that this node is left child  */
    Node deepleftNode;
    void deepestLeftLeafUtil(Node node, int lvl, int maxLevel, boolean isLeft)
    {
        if (node == null)
            return;

        // Update result if this node is left leaf and its level is more than the maxl level of the current result
        if (isLeft != false && node.left == null && node.right == null && lvl > maxLevel)
        {
            deepleftNode = node;
            maxLevel = lvl;
        }

        // Recur for left and right subtrees
        deepestLeftLeafUtil(node.left, lvl + 1, maxLevel, true);
        deepestLeftLeafUtil(node.right, lvl + 1, maxLevel, false);
    }

    /** Returns value of deepest Left node    */
    void deepestLeftLeaf(Node node)
    {
        deepestLeftLeafUtil(node, 0, 0, false);
    }

    /** is balanced tree
     *  Time complexity: O(n2) Space complexity: O(n)
     *  We are calculating maxDepth repeatedly for each node
     */
    public boolean isBalancedBruteForce(Node root) {
        if (root == null)
            return true;
        return (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1
                && isBalancedBruteForce(root.left) && isBalancedBruteForce(root.right));
    }

    /** Better approach than the above one
         Time complexity and space complexity both are O(n)
         We use sentinel value -1 to represent that the tree is unbalanced so, we could avoid unnecessary calculation
     */
    public boolean isBalanced(Node root) {
        return maxDepthBalanced(root) == -1;
    }

    public int maxDepthBalanced(Node root) {
        if (root == null)
            return 0;

        // Each step, we check if the left subtree is unbalanced. If it is unbalanced, we return -1 straight away
        int L = maxDepthBalanced(root.left);
        if (L == -1)
            return -1;

        int R = maxDepthBalanced(root.right);
        if (R == -1)
            return -1;

        // The left and right subtrees are balanced. Check the difference between their max depths.
        // If the absolute difference is less than or equal to 1 then return max value of L and R
        return Math.abs(L - R)  <= 1 ? Math.max(L, R) + 1 : -1;
    }

    public void isValidBST(Node root) {
        System.out.println("isValid " + validBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public boolean validBST(Node node, int low, int high) {
        if (node == null)
            return true;
        return (node.data > low && node.data < high
                && validBST(node.left, low, node.data) && validBST(node.right, node.data, high));
    }

    /** create a BST from a Sorted array
     * Find the mid element, make it root
     * create sortArrayToBST left array and assign it to the root.left
     * create sortArrayToBST right array and assign it to the root.right
     */
    public Node sortArrayToBST(int[] arr) {
        return sortArrayToBST(arr, 0, arr.length - 1);
    }

    public Node sortArrayToBST(int[] arr, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        node.left = sortArrayToBST(arr, start, mid - 1);
        node.right = sortArrayToBST(arr, mid + 1, end);
        return node;
    }

    public static void main(String []args){
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);
        bst.insert(15);
        bst.insert(29);
        bst.insert(22);
        bst.inOrder(bst.root);
        System.out.println();
        bst.inOrderIterative(bst.root);
        bst.preOrder(bst.root);
        System.out.println();
        bst.preOrderIterative(bst.root);
        bst.postOrder(bst.root);
        System.out.println();
        bst.postOrderIterative(bst.root);
        System.out.println();
        bst.insert(49);
        bst.insert(2);
        bst.insert(35);
        bst.inOrder(bst.root);
        System.out.println();
        bst.inOrderIterative(bst.root);
        bst.preOrder(bst.root);
        System.out.println();
        bst.preOrderIterative(bst.root);
        bst.postOrder(bst.root);
        System.out.println();
        bst.postOrderIterative(bst.root);
        System.out.println();
        System.out.println("Max Depth " + bst.maxDepth(bst.root));
        System.out.println("Min Depth " + bst.minDepth(bst.root));
        bst.levelOrder(bst.root);
        System.out.println();
        bst.isValidBST(bst.root);

        bst.remove(15);
        bst.levelOrder(bst.root);
        System.out.println();
        bst.preOrderIterative(bst.root);
        bst.postOrder(bst.root);

        int[] arr = {1,2,3,4,5,6,7};
        Node newNode = bst.sortArrayToBST(arr);
        System.out.println(newNode.data);
        bst.postOrder(newNode);
    }
}