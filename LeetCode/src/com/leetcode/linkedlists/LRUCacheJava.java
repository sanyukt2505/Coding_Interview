package com.leetcode.linkedlists;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheJava<K, V> extends LinkedHashMap<K, V> {
    private int cacheSize;

    private LRUCacheJava(int cacheSize) {
        // initial capacity: 15, load factor: 0.75, accessOrder: true (the ordering mode is access-order) [If it was false: the ordering mode was insertion order]
        super(15, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    public static <K, V> LRUCacheJava<K, V> newInstance(int cacheSize) {
        return new LRUCacheJava<>(cacheSize);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    public static void main(String[] args) {
        // Initialize LRUCache of size 2
        LRUCacheJava<String, String> lruCacheJava = newInstance(2);

        // Insert 3 elements into LRUCache
        lruCacheJava.put("1", "1");
        lruCacheJava.put("2", "2");
        lruCacheJava.put("3", "3");

        // Since the size of LRU Cache is only 2, there should be only two elements printed
        System.out.println(lruCacheJava);
    }
}
