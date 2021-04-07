package com.cci.linkedlists;

/**
 * Created by Vijay on 1/3/16.
 */
public class KthToTheLastRecursive {
    public static void main(String[] args) {
        SLLNode<Integer> node = new SLLNode<>(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(4);
        node.appendToTail(5);
        node.appendToTail(6);
        node.appendToTail(7);
        node.appendToTail(8);
        node.appendToTail(9);
        node.appendToTail(10);

        printNodes(node);
        System.out.println();

        printKthToTheLast(node, 4);
    }

    /**
     * Use Recursion
     * @param node
     * @param k The count to the last element in the list e.g. 3rd to last, 4th to last
     * @return
     */
    private static int printKthToTheLast(SLLNode<Integer> node, int k) {
        // Corner case
        if (node == null) {
            return 0;
        }

        int index = printKthToTheLast(node.next, k) + 1;

        if (index == k) {
            System.out.println(k + "th to the last node is: "+node.data);
        }

        return index;
    }

    private static void printNodes(SLLNode<Integer> node) {
        while (node != null) {
            System.out.print(node.data + "\t");
            node = node.next;
        }
    }
}
