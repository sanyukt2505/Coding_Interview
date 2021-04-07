package com.leetcode.linkedlists;

import java.util.HashMap;

/**
 * The Key to solve this problem is using a doubly linked list. It enables us to move nodes quickly.
 * The LRU Cache is a hash table of keys and doubly linked nodes.
 *  The hash table makes the time of get() to be O(1).
 *  The list of double linked nodes make the nodes adding/removal operations to O(1)
 *      Node at head: most recently used
 *      Node at end: least recently used
 */
class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    int capacity;
    HashMap<Integer, Node> map = new HashMap<>();

    // Define two dummy nodes to take care of overflow
    Node head = null;
    Node end = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    // get access is recent use. On access the element is removed from list (wherever it is) and set at the head
    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }

        return -1;
    }

    private void remove(Node n) {
        if (n.pre != null) { // n is an intermediate node
            n.pre.next = n.next;
        } else { // n is head node
            head = n.next;
        }

        if (n.next != null) { // n is an intermediate node
            n.next.pre = n.pre;
        } else { // n is end node
            end = n.pre;
        }

    }

    // setting a node as head is marking it as most recently used
    private void setHead(Node n) {
        n.next = head;
        n.pre = null;

        if (head != null) {
            head.pre = n;
        }

        head = n;

        if (end == null) {
            end = head;
        }
    }

    public void set(int key, int value) {
        if(map.containsKey(key)) {
            // the entry is already present in the Cache, just get the node and update its value.
            Node old = map.get(key);
            old.value = value;

            // set is an access as well, so remove it from list (wherever it is) and set it as head
            remove(old);
            setHead(old);
        } else {
            // the entry is not present in the cache.
            Node created = new Node(key, value);

            if (map.size() >= capacity) {
                // map size is exceeded, so: remove the end (least recently used) node in the list and set the new node as head
                map.remove(end.key);
                remove(end);
                setHead(created);
            } else {
                // there is space in the map, so: just set the new node node as head (as it is most recently used)
                setHead(created);
            }

            // Update the map with Integer key and corresponding new value
            map.put(key, created);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.set(1, 1);
        cache.set(2, 2);
        cache.set(3, 3);

        System.out.println("Cache hit: " + cache.get(2));
        System.out.println("Cache miss: " + cache.get(1));
    }

}
