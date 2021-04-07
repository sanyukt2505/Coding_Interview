package com.cci.linkedlists;

/**
 * If you have a LinkedList like a -> b -> c -> d -> e Then delete c node
 *  Given: Access to node c only (Not to head)
 *  Output: a list a -> b -> d -> e
 */
public class DeleteMiddleNodeWithoutTraversal {
    public static void main(String[] args) {
        SLLNode<Character> node = new SLLNode<>('a');
        node.appendToTail('b');
        node.appendToTail('c');
        node.appendToTail('d');
        node.appendToTail('e');

        System.out.print("List before deletion of middle node: \t");
        printNodes(node);
        System.out.println();

        SLLNode<Character> middleNode = getMiddleNode(node);
        System.out.println("Middle Node: "+middleNode.data);

        boolean deleted = deleteMiddleNode(middleNode);

        if (deleted) {
            System.out.print("List after deletion of middle node: \t");
            printNodes(node);
        }
        System.out.println();

    }

    private static SLLNode<Character> getMiddleNode(SLLNode<Character> head) {
        SLLNode<Character> node = head;
        int count = 0;

        while (node != null) {
            count++;
            node = node.next;
        }

        SLLNode<Character> middleNode = head;

        for (int i = 0; i < count/2; i++) {
            middleNode = middleNode.next;
        }

        return middleNode;
    }


    /**
     * Just copy the data from the next node to the current node and remove the next node
     * @param node
     * @return
     */
    private static boolean deleteMiddleNode(SLLNode<Character> node) {
        // Corner case
        if (node == null) {
            throw new NullPointerException("List is Null");
        }

        SLLNode<Character> next = node.next;
        node.data = next.data;
        node.next = next.next;

        return true;
    }

    private static void printNodes(SLLNode<Character> node) {
        while (node != null) {
            System.out.print(node.data + "\t");
            node = node.next;
        }
    }
}
