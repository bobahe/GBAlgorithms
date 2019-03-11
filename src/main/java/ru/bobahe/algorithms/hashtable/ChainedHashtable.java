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
        int bucketIndex = key.hashCode() & (buckets.length - 1);

        createListIfNull(bucketIndex);

        Entry<K, V> entry = getEntryByKey(key, bucketIndex);

        if (entry == null) {
            buckets[bucketIndex].add(new Entry<>(key, value));
        } else {
            entry.value = value;
        }
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
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
    private Entry<K, V> getEntryByKey(K key, int bucketIndex) {
        for (Entry e : buckets[bucketIndex]) {
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

    class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
