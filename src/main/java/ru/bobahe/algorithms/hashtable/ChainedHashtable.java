package ru.bobahe.algorithms.hashtable;

import java.util.LinkedList;
import java.util.List;

/**
 * Simple hashtable. It has fixed size buckets array and do allow store only unique keys.
 * @param <K> key
 * @param <V> value
 */
public class ChainedHashtable<K, V> implements HashTable<K, V> {
    /**
     * Default capacity
     */
    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Array to store lists of Entry with key and value
     */
    private List<Entry<K, V>>[] buckets;

    /**
     * Number of elements in collection
     */
    private int size;

    /**
     * Constructs collection with default capacity
     */
    public ChainedHashtable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs collection with specified capacity
     * @param capacity capacity of collection
     */
    @SuppressWarnings("unchecked")
    public ChainedHashtable(int capacity) {
        buckets = new LinkedList[capacity];
    }

    /**
     * Adds entry with specified key and value to collection. If collection already has entry with specified
     * key, the value of entry is changing.
     * @param key key
     * @param value value
     */
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

    /**
     * Returns value of entry with specified key, or null if there is no such entry.
     * @param key key
     * @return value, or null
     */
    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntryByKey(key);

        if (entry != null) {
            return entry.value;
        }

        return null;
    }

    /**
     * Removes entry with specified key from collection.
     * @param key
     * @return true if entry was removed, or false if there is no entry with specified key
     */
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

    /**
     * Returns number of elements in collection
     * @return size of collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if collection is empty, or false.
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns Entry with specified key, or null if there is no such Entry in collection
     * @param key key
     * @return Entry, or null
     */
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

    /**
     * Creates new LinkedList in bucket, if bucket is empty
     * @param bucketIndex index of bucket
     */
    private void createListIfNull(int bucketIndex) {
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new LinkedList<>();
        }
    }

    /**
     * Returns index of bucket for the specified key
     * @param key key
     * @return int
     */
    private int getBucketIndex(K key) {
        // Формулу взял с урока предыдущего курса. Вроде что-то подобное реализовано и в HashMap Java.
        return key.hashCode() & (buckets.length - 1);
    }

    /**
     * Represents entry with key and value to store in collection
     * @param <K> key
     * @param <V> value
     */
    static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
