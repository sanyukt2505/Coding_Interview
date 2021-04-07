package com.ucb.algos.ds.maps.hastables.skiplists;

/**
 * Created by Vijay on 3/6/16.
 */
public interface Map<K,V> {
    int size();
    boolean isEmpty();
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    Iterable<K> keySet();
    Iterable<V> values();
    Iterable<Entry<K,V>> entrySet();
}
