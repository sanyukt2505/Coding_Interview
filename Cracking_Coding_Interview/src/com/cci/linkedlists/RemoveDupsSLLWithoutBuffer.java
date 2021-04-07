package com.cci.linkedlists;

public class RemoveDupsSLLWithoutBuffer {
    public static void main(String[] args) {
        SLLNode<Integer> node = new SLLNode<>(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(1);
        node.appendToTail(4);

        System.out.println("List before Duplicates Removal");
        printNodes(node);
        System.out.println();

        removeDuplicates(node);

        System.out.println("List after Duplicates Removal");
        printNodes(node);
    }

    /**
     * Notice: Unlike RemoveDupsSLL, we don't use buffer (i.e. HashSet) here
     * Instead, we use two pointers:
     *  1. current: the node for which we are identifying the duplicates
     *  2. runner: the pointer which runs through remainder of the list looking for duplicates
     * Runs in O(N2) time since for each current node runner runs through remainder of the list
     * @param node
     */
    private static void removeDuplicates(SLLNode<Integer> node) {
        SLLNode<Integer> current = node;

        // For each current nodes
        while (current != null) {
            // Set runner node to be the next of the current node
            SLLNode<Integer> runner = current.next;

            // For each next node of runner
            while (isNotNull(runner) && runner.next != null) {
                if (runner.next.data == current.data) {
                    // runner's next node's data matches with the current node's data. Skip this node by pointing runner's next node to runner.next.next
                    runner.next = runner.next.next;
                } else {
                    // Traverse to runner's next node
                    runner = runner.next;
                }
            }
            // traverse to next current node
            current = current.next;
        }
    }

    private static boolean isNotNull(SLLNode<Integer> runner) {
        return runner != null;
    }

    private static void printNodes(SLLNode<Integer> node) {
        while (node != null) {
            System.out.print(node.data + "\t");
            node = node.next;
        }
    }
}