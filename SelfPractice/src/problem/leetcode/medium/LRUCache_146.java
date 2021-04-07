package problem.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * LRUCache cache = new LRUCache( 2 );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 */
public class LRUCache_146 {
    class Node {
        public int key;
        public int value;
        public Node next;
        public Node prev;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
        public Node(){}
    }

    private int size;
    private int capacity;
    private Node head, tail;
    private Map<Integer, Node> cache = new HashMap<>();

    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node node) {
        node.next = head.next;
        node.prev = head;
        node.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node removeTail() {
        Node last = tail.prev;
        removeNode(last);
        return last;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);

        if(node != null) {
            node.value = value;
            moveToHead(node);
        } else {
            if(size == capacity) {
                Node tail = removeTail();
                cache.remove(tail.key);
                size--;
            }
            Node newNode = new Node(key, value);
            addNode(newNode);
            cache.put(key, newNode);
            size++;
        }
    }
}