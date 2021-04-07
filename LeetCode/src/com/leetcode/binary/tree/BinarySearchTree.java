package com.leetcode.binary.tree;


import com.leetcode.linkedlists.ListNode;

import java.util.*;

/**
 * Created by Vijay on 2/1/16.
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
	TreeNode<E> root;

	/**
	 * Depth First search: visit yourself, visit left child, visit right child
	 * @param node
	 */
	public void preOrder(TreeNode<E> node) {
		if (node != null) {
			node.visit();
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}

	/**
	 * Perform preOrder traversal without using recursion
	 * @param root
	 * @return ArrayList of node values captured in sequence while performing preOrder traversal
	 */
	public ArrayList<E> preOrderIterative(TreeNode<E> root) {
		// ArrayList containing the node values. The node values are added in a sequence that reflects preOrder
		ArrayList<E> returnList = new ArrayList<>();

		// If root is empty, return empty ArrayList
		if (root == null) {
			return returnList;
		}

		Stack<TreeNode<E>> stack = new Stack<>();
		stack.push(this.root);

		while (!stack.empty()) {
			TreeNode<E> n = stack.pop();
			returnList.add(n.getValue());

			if (n.getRight() != null) {
				stack.push(n.getRight());
			}
			if (n.getLeft() != null) {
				stack.push(n.getLeft());
			}
		}

		return returnList;
	}

	/**
	 * Depth First search: visit left child, visit yourself, visit right child
	 * @param node
	 */
	public void inOrder(TreeNode<E> node) {
		if (node != null) {
			inOrder(node.getLeft());
			node.visit();
			inOrder(node.getRight());
		}
	}

	public ArrayList<E> inOrderIterative(TreeNode<E> root) {
		ArrayList<E> returnList = new ArrayList<>();

		if (root == null) {
			return returnList;
		}

		Stack<TreeNode<E>> stack = new Stack<>();
		TreeNode<E> node = root;

		// Stack is initially empty. But, node is not.
		while (!stack.empty() || node != null) {
			if (node != null) {
				stack.push(node);
				node = node.getLeft();
			} else {
				TreeNode<E> temp = stack.pop();
				returnList.add(temp.getValue());
				node = temp.getRight();
			}
		}

		return returnList;
	}

	/**
	 * Depth First search: visit left child, visit right child, visit yourself
	 * @param node
	 */
	public void postOrder(TreeNode<E> node) {
		if (node != null) {
			postOrder(node.getLeft());
			postOrder(node.getRight());
			node.visit();
		}
	}

	/**
	 * Breadth First Search: visit nodes at the same level, then visit nodes on the next level down in the tree
	 * @param root
	 */
	public void levelOrder(TreeNode<E> root) {
		Queue<TreeNode<E>> q = new LinkedList<>();
		q.add(root);

		while (!q.isEmpty()) {
			TreeNode<E> curr = q.remove();
			if (curr != null) {
				curr.visit();
				q.add(curr.getLeft());
				q.add(curr.getRight());
			}
		}
	}

    public List<List<E>> levelOrderTrace(TreeNode<E> root) {
        Queue<TreeNode<E>> q = new LinkedList<>();
        List<List<E>> wrapList = new LinkedList<>();

        if(root == null) return wrapList;
        q.offer(root);

        while (!q.isEmpty()) {
            int levelNum = q.size();
            List<E> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                if (q.peek().getLeft() != null) {
                    q.offer(q.peek().getLeft());
                }
                if (q.peek().getRight() != null) {
                    q.offer(q.peek().getRight());
                }
                subList.add(q.poll().getValue());
            }
            wrapList.add(subList);
        }
        return wrapList;
    }

    /**
     * Problem: https://leetcode.com/problems/binary-tree-postorder-traversal/
     * Solution link: https://leetcode.com/discuss/9736/accepted-code-with-explaination-does-anyone-have-better-idea
     * @param root
     * @return Trace obtained by traverse the tree in post order
     */
    public List<E> postOrderTraversal(TreeNode<E> root) {
        List<E> results = new ArrayList<E>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                results.add(root.getValue());
                root = root.getRight();
            } else {
                root = stack.pop().getLeft();
            }
        }

        Collections.reverse(results);
        return results;
    }

	public boolean isValidBST(TreeNode<E> root) {
		return valid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean valid(TreeNode<E> p, Integer low, Integer high) {
		if (p == null) return true;
		// valid(p.getLeft(), low, Integer.parseInt(p.getValue().toString())) : Nodes to the left of p should be greater than low and less than p node's value
		// valid(p.getRight(), Integer.parseInt(p.getValue().toString()), high) : Nodes to the right of p should be greater than p node's value and less than high
		return Integer.parseInt(p.getValue().toString()) > low
				&& Integer.parseInt(p.getValue().toString()) < high
				&& valid(p.getLeft(), low, Integer.parseInt(p.getValue().toString()))
				&& valid(p.getRight(), Integer.parseInt(p.getValue().toString()), high);
	}

	public int maxDepth(TreeNode<E> root) {
		if (root == null) return 0;
		return Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight())) + 1;
	}

	public int minDepth(TreeNode<E> root) {
		if (root == null) return 0;
		if (root.getLeft() == null) return minDepth(root.getRight()) + 1;
		if (root.getRight() == null) return minDepth(root.getLeft()) + 1;
		return Math.min(minDepth(root.getLeft()), minDepth(root.getRight())) + 1;
	}

	// Time complexity: O(n2) Space complexity: O(n)
	// We are calculating maxDepth repeatedly for each node
	public boolean isBalancedBruteForce(TreeNode<E> root) {
		if (root == null) return true;
		return Math.abs(maxDepth(root.getLeft()) - maxDepth(root.getRight())) <= 1 && isBalancedBruteForce(root.getLeft()) && isBalancedBruteForce(root.getRight());
	}

	// Better approach than the above one
	// Time complexity and space complexity both are O(n)
	// We use sentinel value -1 to represent that the tree is unbalanced so, we could avoid unnecessary calculation
	public boolean isBalanced(TreeNode<E> root) {
		return maxDepthBalanced(root) != -1;
	}

	// An example of binary recursion
	private int maxDepthBalanced(TreeNode<E> root) {
		// Base condition
		if (root == null) return 0;

		// Each step, we check if the left subtree is unbalanced. If it is unbalanced, we return -1 straight away
		int L = maxDepthBalanced(root.getLeft());
		if (L == -1) return -1;

		// Each step, we check if the right subtree is unbalanced. If it is unbalanced, we return -1 straight away
		int R = maxDepthBalanced(root.getRight());
		if (R == -1) return -1;

		// The left and right subtrees are balanced. Check the difference between their max depths.
		// If the absolute difference is less than or equal to 1 then return max value of L and R
		return (Math.abs(L - R) <= 1) ? (Math.max(L, R) + 1) : -1;
	}

	public TreeNode<Integer> sortedArrayToBST(Integer[] num) {
		return sortedArrayToBST(num, 0 , num.length - 1);
	}

	private TreeNode<Integer> sortedArrayToBST(Integer[] arr, int start, int end) {
	  // Base condition
	  if (start > end) return null;
	  // Calculate mid
	  int mid = (start + end) / 2;

	  // Create parent
	  TreeNode<Integer> node = new TreeNode<>(arr[mid]);
	  // set left child
	  node.setLeft(sortedArrayToBST(arr, start, mid-1));
	  // set right child
	  node.setRight(sortedArrayToBST(arr, mid+1, end));

	  return node;
	}

	private static ListNode<Integer> list;

	private static TreeNode<Integer> sortedListToBST(int start, int end) {
	  // Base condition
	  if (start > end) return null;
	  // Calculate the mid
	  int mid = (start + end) / 2;

	  // Create left child first
	  TreeNode<Integer> leftChild = sortedListToBST(start, mid - 1);

	  // Create the parent now with the list's element as value
	  TreeNode<Integer> parent = new TreeNode<>(list.getElement());

	  // set the leftChild object as parent's left child
	  parent.setLeft(leftChild);

	  // Traverse to the next element in the list
	  list = list.getNext();

	  // recurse over the right range mid+1 to end to create right object in the tree
	  parent.setRight(sortedListToBST(mid + 1, end));

	  return parent;
	}

	/**
	 * Traverse down the tree until the end is reached OR the element is found
	 * @param toFind
	 * @return
	 */
	public boolean contains(E toFind) {
		// Get a local reference to root. DON'T EVER loose the root element. It is holding your tree together. :)
		TreeNode<E> curr = this.root;

		// Declare a value for comparison result
		int comp;
		while (curr != null) {
			// Invoke compareTo on element toFind. Pass curr element's value as input
			comp = toFind.compareTo(curr.getValue());

			if (comp < 0) {
				// Element toFind is less than curr element's value
				curr = curr.getLeft();
			} else if (comp > 0) {
				// Element toFind is greater than curr element's value
				curr = curr.getRight();
			} else {
				// Element toFind is found and is equal to the curr value
				return true;
			}
		}
		// If the control has reached here, then element is not found
		return false;
	}

	/**
	 * Insert element into a Binary Search Tree
	 * @param toInsert
	 * @return result of insertion. "true" if element is inserted successfully && "false" if element already exists in the tree
	 */
	public boolean insert(E toInsert) {
		TreeNode<E> curr = root;
		int comp = toInsert.compareTo(curr.getValue());

		/**
		 * Remember: condition while (curr != null) does not work here BECAUSE once curr becomes null we CAN'T attach the node either as left or right node.
		 *  We traverse the tree comparing the existing elements in the tree with the value to be inserted
		 *  So, If the element toInsert value is less than current node's value, we verify that curr.getLeft() is not null before traversing down that path
		 *  AND, If the element toInsert value is greater than current node's value, we verify that curr.getLRight() is not null before traversing down that path
		 */
		while (comp < 0 && curr.getLeft() != null ||
				comp > 0 && curr.getRight() != null) {
			if (comp < 0) {
				curr = curr.getLeft(); // If toInsert is less than 0, traverse down the left subtree
			} else if (comp > 0) {
				curr = curr.getRight(); // If toInsert is greater than 0, traverse down the right subtree
			}
			// update the comp value for further navigation
			comp = toInsert.compareTo(curr.getValue());
		}

		/**
		 * Remember: At this point, we can compare the value of comp with 0 and set new node as either left or right subtree of the curr node
		 *  If the comp value is zero, it signifies that the node already exists in the tree. We return false to indicate that.
		 */
		if (comp < 0) {
			curr.addLeftChild(toInsert);
		} else if (comp > 0) {
			curr.addRightChild(toInsert);
		} else {
            // The node is already present in the tree
            return false;
        }

		return true;
	}

	/**
	 * Remove element from a Binary Search Tree
	 * @param toRemove
	 * @return result of insertion. "true" if element is inserted successfully && "false" if element already exists in the tree
	 */
	public boolean remove(E toRemove) {
		TreeNode<E> curr = root;
		TreeNode<E> removedNode = remove(toRemove, curr);
		if (removedNode != null) {
			return true;
		} else {
			return false;
		}
	}

	private TreeNode<E> remove(E toRemove, TreeNode<E> curr) {
		if (curr == null) {
		   return curr;
		}

		if (toRemove.compareTo(curr.getValue()) < 0) {
			curr.setLeft(remove(toRemove, curr.getLeft()));
		} else if (toRemove.compareTo(curr.getValue()) > 0) {
			curr.setRight(remove(toRemove, curr.getRight()));
		} else if (curr.getLeft() != null && curr.getRight() != null) {
			curr.setValue(findMin(curr.getRight()).getValue());
			curr.setRight(remove(curr.getValue(), curr.getRight()));
		} else {
			curr = (curr.getLeft() != null) ? curr.getLeft() : curr.getRight();
		}

		return curr;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * @param curr the node that roots the tree.
	 * @return node containing the smallest item.
	 */
	private TreeNode<E> findMin( TreeNode<E> curr )
	{
		if( curr == null )
			return null;
		else if( curr.getLeft() == null )
			return curr;
		return findMin( curr.getLeft() );
	}

	public TreeNode<E> upsideDownBinaryTree(TreeNode<E> root) {
        return dfsBottomUp(root, null);
    }

    /**
	 * Flip a BST
     * Think recursively in bottom-up fashion.
     * If we reassign the bottom-level nodes before the upper ones, we won't have to make copies and worry about overwriting something.
     * We know that the new root would be the left-most leaf node, so we begin reassignment here.
     * @param p
     * @param parent
     * @return
     */
    // TO-Read
    private TreeNode<E> dfsBottomUp(TreeNode<E> p, TreeNode<E> parent) {
        // Base condition: if the root is passed as null, return parent
        if (p == null) {
            return parent;
        }

        TreeNode<E> root = dfsBottomUp(p.getLeft(), p);
        p.setLeft((parent == null)? parent : parent.getRight());
        p.setRight(parent);
        return root;
    }

    public List<String> binaryTreePaths(TreeNode<String> root) {
        List<String> answer = new ArrayList<>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }

    private void searchBT(TreeNode<String> root, String path, List<String> answer) {
        if (root.getLeft() == null && root.getRight() == null) answer.add(path + root.getValue());
        if (root.getLeft() != null) searchBT(root.getLeft(), path + root.getValue() + "->", answer);
        if (root.getRight() != null) searchBT(root.getRight(), path + root.getValue() + "->", answer);
    }

    /**
     * Solution to: https://leetcode.com/problems/invert-binary-tree/
     * @param root
     * @return
     */
    public TreeNode<E> invertTree(TreeNode<E> root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        // add root to the queue
        queue.offer(root);

        while (!queue.isEmpty()) {
            // get the first element from queue
            TreeNode<E> node = queue.poll();

            TreeNode<E> temp = node.getLeft();
            node.setRight(node.getLeft());
            node.setLeft(temp);

            if (node.getLeft() != null) {
                queue.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.offer(node.getRight());
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
    public boolean isSameTree(TreeNode<E> p, TreeNode<E> q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.getValue() == q.getValue()) {
            return isSameTree(p.getLeft(), q.getLeft()) && isSameTree(p.getRight(), q.getRight());
        }

        return false;
    }

    /**
     * Solution to the problem: Lowest Common Ancestor of a Binary Search Tree (https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
     * Approach: Walk down from whole tree's root as long as both p and q are in the same subtree (meaning their values are both smaller and larger than root's value).
     *          This walks straight from root to LCA, not looking at the rest of the tree
     * @param root
     * @param p
     * @param q
     * @return TreeNode that is Lowest common ancestor of
     */
    public TreeNode<E> lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        while ((Integer.parseInt(root.getValue().toString()) - Integer.parseInt(p.getValue().toString())) *
                (Integer.parseInt(root.getValue().toString()) - Integer.parseInt(q.getValue().toString())) > 0) {
            // Notice: The root value changes to
            //  1.  root.left IF p.left.val < root.val
            //  2.  root.right IF p.left.val > root.val
            root = Integer.parseInt(p.getValue().toString()) < Integer.parseInt(root.getValue().toString()) ? root.getLeft() : root.getRight();
        }
        return root;
    }

    /**
     * Solution to the problem: Lowest Common Ancestor of a Binary Search Tree (https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
     * Solution discussion: https://discuss.leetcode.com/topic/18566/my-java-solution-which-is-easy-to-understand
     * @return TreeNode that is Lowest common ancestor of
     */
    public static TreeNode lowestCommonAncestorBinaryTree(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if(root == null) return null;
        if(root == p || root == q)  return root;

        TreeNode left = lowestCommonAncestorBinaryTree(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestorBinaryTree(root.getRight(), p, q);

        if(left != null && right != null) return root;
        if(left == null && right == null) return null;
        return left != null ? left : right;
    }

    /**
     * Solution to the Unique Binary Trees count problem: https://leetcode.com/problems/unique-binary-search-trees/
     * Solution description: https://discuss.leetcode.com/topic/8398/dp-solution-in-6-lines-with-explanation-f-i-n-g-i-1-g-n-i
     *
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
    public boolean isSymmetricRecursive(TreeNode<Integer> root) {
        // If root is empty: tree is symmetric
        if (root == null) return true;

        return isMirror(root.getLeft(), root.getRight());
    }

    private boolean isMirror(TreeNode<Integer> p, TreeNode<Integer> q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        return (p.getValue() == q.getValue()) && isMirror(p.getLeft(), q.getRight()) && isMirror(p.getRight(), q.getLeft());
    }

    /**
     * Iterative solution to the 'is tree symmetric' problem: https://leetcode.com/problems/symmetric-tree/
     * Solution link: https://discuss.leetcode.com/topic/5941/recursive-and-non-recursive-solutions-in-java/16
     */
    public boolean isSymmetricIterative(TreeNode<Integer> root) {
        // If root is empty: tree is symmetric
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        if (!process(root.getLeft(), root.getRight(), stack)) return false;

        while (!stack.isEmpty()) {
            if (stack.size() % 2 != 0) return false;

            TreeNode<Integer> p = stack.pop();
            TreeNode<Integer> q = stack.pop();

            if (p.getValue() != q.getValue()) return false;

            if (!process(p.getLeft(), q.getRight(), stack)) return false;
            if (!process(p.getRight(), q.getLeft(), stack)) return false;
        }

        return true;
    }

    private boolean process(TreeNode<Integer> a, TreeNode<Integer> b, Stack<TreeNode> stack) {
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
    public static boolean hasPathSum(TreeNode<Integer> root, int sum) {
        if (root == null) return false;

        if (root.getLeft() == null && root.getRight() == null) {
            return  root.getValue() == sum;
        }

        return hasPathSum(root.getLeft(), sum - root.getValue()) || hasPathSum(root.getRight(), sum - root.getValue());
    }

    /**
     * Solution to the maximum path sum problem: https://leetcode.com/problems/binary-tree-maximum-path-sum/
     * Solution description: https://discuss.leetcode.com/topic/4407/accepted-short-solution-in-java
     *  A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
     *  A recursive method maxPathDown(TreeNode node):
     *      (1) computes the maximum path sum with highest node is the input node, update maximum if necessary
     *      (2) returns the maximum sum of the path that can be extended to input node's parent.
     */
    static int maxValue;

    public static int maxPathSum(TreeNode<Integer> root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private static int maxPathDown(TreeNode<Integer> node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.getLeft()));
        int right = Math.max(0, maxPathDown(node.getRight()));
        maxValue = Math.max(maxValue, left + right + node.getValue());
        return Math.max(left, right) + node.getValue();
    }

    /**
	 * Client program for all BinarySearchTree functions
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] sortedArr = {11, 22, 33, 44, 55};
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

		System.out.println("Converting sorted array into BST");
		TreeNode<Integer> rootNode = binarySearchTree.sortedArrayToBST(sortedArr);
		// To facilitate next operations, lets set the instance variable root to the rootNode returned by previous invocation
		binarySearchTree.root = rootNode;
		System.out.println("");

		System.out.println("PreOrder traversal using Recursion: ");
		binarySearchTree.preOrder(rootNode);
		System.out.println("");

		System.out.println("PreOrder traversal without using Recursion: ");
		ArrayList<Integer> preOrderTrace = binarySearchTree.preOrderIterative(rootNode);
		for (Integer next : preOrderTrace) {
			System.out.println(next);
		}
		System.out.println("");

		System.out.println("InOrder traversal using Recursion: ");
		binarySearchTree.inOrder(rootNode);
		System.out.println("");

		System.out.println("InOrder traversal without using Recursion: ");
		ArrayList<Integer> inOrderTrace = binarySearchTree.inOrderIterative(rootNode);
		for (Integer next : inOrderTrace) {
			System.out.println(next);
		}
		System.out.println("");

		System.out.println("PostOrder traversal using Recursion: ");
		binarySearchTree.postOrder(rootNode);
		System.out.println("");

		System.out.println("Level Order traversal without using Recursion: ");
		binarySearchTree.levelOrder(rootNode);
		System.out.println("");

        System.out.println("Level Order traversal trace without using Recursion: ");
        System.out.println(binarySearchTree.levelOrderTrace(rootNode));
        System.out.println("");

        System.out.println("PostOrder traversal without using Recursion: ");
        List<Integer> postOrderTrace = binarySearchTree.postOrderTraversal(rootNode);
        for (Integer next : postOrderTrace) {
            System.out.println(next);
        }
        System.out.println("");

		System.out.println("Does Tree contain element 11?: " + binarySearchTree.contains(11));
		System.out.println("");

		System.out.println("Does Tree contain element 50?: " + binarySearchTree.contains(50));
		System.out.println("");

		System.out.println("Is binarySearchTree a Valid BST?: " + binarySearchTree.isValidBST(rootNode));
		System.out.println("");

		System.out.println("Max Depth of the BST: " + binarySearchTree.maxDepth(rootNode));
		System.out.println("");

		System.out.println("Min Depth of the BST: " + binarySearchTree.minDepth(rootNode));
		System.out.println("");

		System.out.println("Is the BST balanced?: " + binarySearchTree.isBalanced(rootNode));
		System.out.println("");

		System.out.println("Inserted 50 in the tree?: " + binarySearchTree.insert(50));
		System.out.println("Does tree contain the value 50? : " + binarySearchTree.contains(50));

		System.out.println("Is the BST balanced?: " + binarySearchTree.isBalanced(rootNode));
		System.out.println("");

		System.out.println("Trying to remove Node 50. Removed?: " + binarySearchTree.remove(50));
		System.out.println("");

		System.out.println("Is the BST balanced?: " + binarySearchTree.isBalanced(rootNode));
		System.out.println("");

		System.out.println("Max Path Sum: " + binarySearchTree.maxPathSum(rootNode));
		System.out.println("");

        Integer[] sortedArrSecond = {1, 2, 3, 4, 6, 7, 9};
        BinarySearchTree<Integer> binarySearchTreeSecond = new BinarySearchTree<>();

        System.out.println("Converting sortedArrSecond into BST");
        TreeNode<Integer> invertedBinaryTreeNode = binarySearchTreeSecond.sortedArrayToBST(sortedArrSecond);
        invertedBinaryTreeNode = binarySearchTreeSecond.invertTree(invertedBinaryTreeNode);
        System.out.println(invertedBinaryTreeNode.getLeft().getValue());

        Integer[] p = {1, 2, 3, 4, 6, 7, 9};
        TreeNode pTreeRootNode = binarySearchTree.sortedArrayToBST(p);

        Integer[] q = {1, 2, 3, 4, 6, 7, 9};
        TreeNode qTreeRootNode = binarySearchTree.sortedArrayToBST(q);

        System.out.println("Are Trees Equal?: " + binarySearchTree.isSameTree(pTreeRootNode, qTreeRootNode));
        System.out.println("");

        int countOfUniqueTrees = numTrees(3);
        System.out.println("Count of Unique Trees for sequence of 3 numbers: " + countOfUniqueTrees);
        System.out.println("");

        boolean hasPathSum = hasPathSum(binarySearchTree.root, 66);
        System.out.println("Has binary tree path with sum 66?: " + hasPathSum);
        System.out.println("");

        int maxPathSum = maxPathSum(binarySearchTree.root);
        System.out.println("Maximum Path Sum: " + maxPathSum);
        System.out.println("");

    }
}
