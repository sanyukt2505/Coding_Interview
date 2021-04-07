package algos;

public class DoubleLinkList {

    private static class DoubleNode {
        public int data;
        public DoubleNode next;
        public DoubleNode previous;

        public DoubleNode (int d) {
            data = d;
        }
    }

    public DoubleNode first;
    public DoubleNode last;

    public DoubleLinkList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(int d) {
        DoubleNode node = new DoubleNode(d);

        if (!isEmpty()) {
            first.previous = node;
        } else {
            last = node;
        }
        node.next = first;
        first = node;
    }

    public void insertLast(int d) {
        DoubleNode node = new DoubleNode(d);

        if (isEmpty()) {
            first = node;
        } else {
            node.previous = last;
            last.next = node;
        }
        last = node;
    }

    public void insertAfter(int key, int d) {
        DoubleNode node = new DoubleNode(d);

        DoubleNode curr = first;
        while (curr.next != null && curr.data != key) {
            curr = curr.next;
        }

        if (curr == last) {
            curr.next = node;
            node.previous = last;
            last = node;
        } else {
            node.next = curr.next;
            curr.next.previous = node;
            node.previous = curr;
            curr.next = node;
        }
    }

    public int deleteFirst() {
        DoubleNode temp = first;

        if (first == null) {
            last = null;
            return -1;
        } else {
            first.next.previous = null;
            first = first.next;
        }
        return temp.data;
    }

    public int deleteLast() {
        DoubleNode temp = last;

        if (last == null) {
            first = null;
            return -1;
        } else {
            last.previous.next = null;
            last = last.previous;
        }
        return temp.data;
    }

    public int deleteKey(int key) {
        DoubleNode curr = first;

        while (curr.data != key) {
            curr = curr.next;

            if (curr == null)
                return -1;
        }

        if (curr == first) {
            first = curr.next;
        } else {
            curr.previous.next = curr.next;
        }

        if (curr == last) {
            last = curr.previous;
        } else {
            curr.next.previous = curr.previous;
        }
        return curr.data;
    }

    public void displayDll() {
        DoubleNode curr = first;

        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String []args){
        DoubleLinkList dll = new DoubleLinkList();
        dll.insertFirst(11);
        dll.insertLast(20);
        dll.insertAfter(11, 12);
        dll.insertAfter(20, 22);
        dll.displayDll();
        for (int i=1; i <= 10; i++)
            dll.insertFirst(i);
        dll.displayDll();
        System.out.println(dll.deleteFirst());
        System.out.println(dll.deleteLast());
        dll.displayDll();
        System.out.println(dll.deleteKey(11));
        dll.displayDll();
    }
}
