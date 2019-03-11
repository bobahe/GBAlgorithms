package ru.bobahe.algorithms.hashtable;

public interface HashTable<K, V> {
    void put(K key, V value);
    boolean remove(K key);

    V get(K key);

    int size();
    boolean isEmpty();
}
