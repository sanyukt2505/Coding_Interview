package com.leetcode.binary.tree;

/**
 * Created by Vijay on 2/1/16.
 */
public class TreeNode<E> {
    private E value;
    private TreeNode<E> parent;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E value, TreeNode<E> parent) {
        this.value = value;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    public TreeNode(E value) {
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public TreeNode<E> addLeftChild(E value) {
        // Notice we are assigning new TreeNode to this node's left child: this.left = ..;
        this.left = new TreeNode<>(value, this);
        return this.left;
    }

    public TreeNode<E> addRightChild(E value) {
        // Notice we are assigning new TreeNode to this node's left child: this.right = ..;
        this.right = new TreeNode<>(value, this);
        return this.right;
    }

    public void visit() {
        System.out.println("Visited: " + this.value);
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TreeNode<E> getParent() {
        return this.parent;
    }

    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }

    public TreeNode<E> getLeft() {
        return this.left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return this.right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

}
