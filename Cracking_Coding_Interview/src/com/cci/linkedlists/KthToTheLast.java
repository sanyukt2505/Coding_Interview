package com.cci.linkedlists;

public class KthToTheLast {
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

        SLLNode<Integer> kthNode = kthToTheLast(node, 4);
        System.out.println("kth to the last node: "+kthNode.data);
    }

    /**
     * Use the "Runner" technique
     * @param node
     * @param k The count to the last element in the list e.g. 3rd to last, 4th to last
     * @return
     */
    private static SLLNode<Integer> kthToTheLast(SLLNode<Integer> node, int k) {
        // Corner case
        if (node == null) {
            throw new NullPointerException("List is Null");
        }

        SLLNode<Integer> current = node;
        SLLNode<Integer> runner = node;

        for (int count = 0; count < k; count++) {
            runner = runner.next;
        }

        while (runner != null) {
            current = current.next;
            runner = runner.next;
        }

        return current;
    }

    private static void printNodes(SLLNode<Integer> node) {
        while (node != null) {
            System.out.print(node.data + "\t");
            node = node.next;
        }
    }
}
