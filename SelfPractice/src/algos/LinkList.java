package algos;

public class LinkList{
    private static class LinkNode{
        public int data;
        public LinkNode next;

        public LinkNode (int a) {
            data = a;
        }
    }

    public LinkNode first;

    public LinkList() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertAtFirst(int d) {
        LinkNode newNode = new LinkNode(d);
        newNode.next = first;
        first = newNode;
    }

    public int deleteFirst() {
        LinkNode temp = first;
        first = first.next;
        return temp.data;
    }

    public void displayList() {
        LinkNode curr = first;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public int deleteSpecific(int d) {
        LinkNode curr = first;
        LinkNode prev = first;
        while (curr != null) {
            if (curr.data == d) {
                prev.next = curr.next;
                return curr.data;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return -1;
    }

    public void insertSorted(int d) {
        LinkNode newNode = new LinkNode(d);
        LinkNode curr = first;
        LinkNode prev = null;
        while (curr != null && curr.data > d) {
            prev = curr;
            curr = curr.next;
        }

        if (prev == null) {
            first = newNode;
            newNode.next = null;
        }
        else {
            newNode.next = curr;
            prev.next = newNode;
        }
    }

    /**
     * Recursive Solution
     * @param head
     * @return
     */
    private static LinkNode reverseList(LinkNode head) {
        if (head == null || head.next == null) return head;

        // get second node
        LinkNode second = head.next;

        // set first's next to null
        head.next = null;

        LinkNode rest = reverseList(second);
        second.next = head;

        return rest;
    }

    /**
     * Iterative Solution
     * @param head
     * @return
     */
    private static LinkNode reverseListIterative(LinkNode head) {
        LinkNode newHead = null;

        while (head != null) {
            LinkNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }

        return newHead;
    }

    public static void main(String []args) {
        LinkList ll = new LinkList();
        for (int i=0; i < 10; i++)
            ll.insertAtFirst(i);

        ll.displayList();
        System.out.println("----");
        ll.deleteFirst();
        ll.deleteSpecific(5);
        System.out.println("----");
        ll.displayList();
        ll.insertSorted(6);
        System.out.println("----");
        ll.displayList();
    }
}
