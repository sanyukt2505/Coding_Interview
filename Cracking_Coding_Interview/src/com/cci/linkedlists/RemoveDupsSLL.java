package com.cci.linkedlists;

import java.util.HashSet;

public class RemoveDupsSLL {
    public static void main(String[] args) {
        // Create Sample Linked List with duplicate values
        SLLNode<Integer> node = new SLLNode<>(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(1);
        node.appendToTail(4);

        System.out.println("Original List: ");
        printList(node);
        System.out.println();

        // Remove nodes with duplicate values
        removeDuplicates(node);

        System.out.println("After duplicates removal: ");
        printList(node);
    }

    private static void printList(SLLNode<Integer> node) {
        while (node != null) {
            System.out.print(node.data + "\t");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * User HashSet as a buffer. But runs in O(N) time as we traverse the list only once.
     * @param node
     */
    private static void removeDuplicates(SLLNode<Integer> node) {
        // HashSet accepts only unique values (for Integer objects in this case)
        HashSet<Integer> set = new HashSet<>();

        // Maintain a previous node reference
        SLLNode previous = null;

        // Traverse the complete List
        while (node != null) {
            // If the HashSet set already contains the node's data
            if (set.contains(node.data)) {
                // Simply point previous.next to node.next (i.e. the next pointer of node with duplicate data)
                previous.next = node.next;
            } else {
                // HashSet doesn't contain the node's data. Add node's data into HashSet
                set.add(node.data);

                // Update the previous node to the current node
                previous = node;
            }
            node = node.next;
        }
    }
}
