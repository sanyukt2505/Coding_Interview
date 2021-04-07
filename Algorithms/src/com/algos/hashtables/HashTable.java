package com.algos.hashtables;

/**
 * Created by Vijay on 4/23/16.
 */
public class HashTable {

    private HashItem[] hashTable;

    /**
     * Initialize hashTable which is an array of buckets (of HashItems)
     */
    public HashTable() {
        this.hashTable = new HashItem[Constants.TABLE_SIZE];
    }

    public int get(int key) {
        // Use of module function to calculate Hash
        int hash = key % Constants.TABLE_SIZE; // Ideally we should use a prime number in this formula

        if (this.hashTable[hash] == null) {
            return -1;
        } else {
            HashItem hashItem = this.hashTable[hash];
            // Since, we are using chaining approach to solve the cases of hash collisions, we have to navigate through linked list of HashItems
            while (hashItem != null && hashItem.getKey() != key) {
                hashItem = hashItem.getNextHashItem();
            }

            if (hashItem == null) {
                return -1;
            } else {
                return hashItem.getValue();
            }
        }
    }

    public void put(int key, int value) {
        int hash = key % Constants.TABLE_SIZE;

        if (this.hashTable[hash] == null) {
            // bucket is empty
            this.hashTable[hash] = new HashItem(key, value);
        } else {
            // Collision situation: There is already a HashItem at this index
            HashItem hashItem = this.hashTable[hash];

            while (hashItem.getNextHashItem() != null && hashItem.getKey() != key) {
                hashItem = hashItem.getNextHashItem();
            }

            if (hashItem.getKey() == key) {
                hashItem.setValue(value);
            } else {
                hashItem.setNextHashItem(new HashItem(key, value));
            }
        }
    }
}
