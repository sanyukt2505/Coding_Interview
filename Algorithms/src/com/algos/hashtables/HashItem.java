package com.algos.hashtables;

public class HashItem {
    // Each HashItem has a key and a value
    private int key;
    private int value;

    // To solve Hash collisions, we are using a chaining approach. So, a bucket in HashTable would have a linked list of HashItems
    // The following pointer is next item in the linked list
    private HashItem nextHashItem;

    public HashItem(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HashItem getNextHashItem() {
        return nextHashItem;
    }

    public void setNextHashItem(HashItem nextHashItem) {
        this.nextHashItem = nextHashItem;
    }
}
