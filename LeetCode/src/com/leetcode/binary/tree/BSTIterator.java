package com.leetcode.binary.tree;

import java.util.Stack;

/**
 * Created by Vijay on 6/12/16.
 */
public class BSTIterator<E> {
    private Stack<TreeNode<E>> stack = new Stack<>();

    public BSTIterator(TreeNode<E> root) {
        pushAll(root);
    }

    /**
     * @return whether we have a next small element
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next small number
     */
    public E next() {
        TreeNode<E> tempNode = stack.pop();
        pushAll(tempNode.getRight());
        return tempNode.getValue();
    }

    private void pushAll(TreeNode<E> node) {
        for (;node != null; stack.push(node), node = node.getLeft());
    }
}
