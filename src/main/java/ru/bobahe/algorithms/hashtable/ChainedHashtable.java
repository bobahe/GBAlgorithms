package ru.bobahe.algorithms.hashtable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ChainedHashtable<K, V> implements HashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private List<Entry<K, V>>[] buckets;

    private int size;

    public ChainedHashtable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ChainedHashtable(int capacity) {
        buckets = new LinkedList[capacity];
    }

    @Override
    public void put(K key, V value) {
        createListIfNull(getBucketIndex(key));

        Entry<K, V> entry = getEntryByKey(key);

        if (entry == null) {
            buckets[getBucketIndex(key)].add(new Entry<>(key, value));
            size++;
        } else {
            entry.value = value;
        }
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntryByKey(key);

        if (entry != null) {
            return entry.value;
        }

        return null;
    }

    @Override
    public boolean remove(K key) {
        Entry<K, V> entry = getEntryByKey(key);

        if (entry != null) {
            if (buckets[getBucketIndex(key)].remove(entry)) {
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V> getEntryByKey(K key) {
        if (buckets[getBucketIndex(key)] == null) {
            return null;
        }

        for (Entry e : buckets[getBucketIndex(key)]) {
            if (e.key.equals(key)) {
                return e;
            }
        }

        return null;
    }

    private void createListIfNull(int bucketIndex) {
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        return key.hashCode() & (buckets.length - 1);
    }

    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
