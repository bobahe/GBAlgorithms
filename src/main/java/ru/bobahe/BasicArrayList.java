package ru.bobahe;

import java.util.Arrays;

public class BasicArrayList<E extends Object & Comparable<E>> implements BasicList<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final int INCORRECT_INDEX = -1;

    private E[] data;
    private int pointer;

    public BasicArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public BasicArrayList(E[] array) {
        this(array.length);
        System.arraycopy(array, 0, this.data, 0, array.length);
        this.pointer = this.data.length;
    }

    @SuppressWarnings("unchecked")
    public BasicArrayList(int capacity) {
        this.data = (E[]) new Object[capacity];
    }

    @Override
    public void add(E element) {
        grow();

        data[pointer++] = element;
    }

    @Override
    public E get(int index) {
        if (index >= pointer || index < 0)
            throw new ArrayIndexOutOfBoundsException(index);

        return data[index];
    }

    @Override
    public boolean contains(E element) {
        return getIndex(element) != -1;
    }

    @Override
    public int indexOf(E element) {
        return getIndex(element);
    }

    /**
     * Performs binary search in BasicArrayList. BasicArrayList must be sorted!
     * If BasicArrayList isn't sorted, result will be unexpected.
     *
     * @param element element to be found
     * @return index of found element if exists, or -1
     */
    @Override
    public int binarySearch(E element) {
        int highEdge = pointer - 1;
        int lowEdge = 0;

        while (lowEdge <= highEdge) {
            int middle = (highEdge + lowEdge) / 2;

            if (data[middle].equals(element)) {
                return middle;
            }

            if (data[middle].compareTo(element) < 0) {
                lowEdge = middle + 1;
            } else if (data[middle].compareTo(element) > 0) {
                highEdge = middle - 1;
            }
        }

        return INCORRECT_INDEX;
    }

    @Override
    public void remove(int index) {
        // shift elements on right hand
        for (int i = index + 1; i < pointer; i++) {
            data[i - 1] = data[i];
        }

        pointer--;
    }

    @Override
    public void remove(E element) {
        int index = getIndex(element);

        if (index != -1)
            remove(index);
    }

    @Override
    public int size() {
        return pointer;
    }

    @Override
    public void sortBubble() {
        for (int i = 1; i < pointer - 1; i++) {
            for (int j = 0; j <= pointer - i - 1; j++) {
                if (data[j].compareTo(data[j + 1]) > 0)
                    exchange(j, j + 1);
            }
        }
    }

    @Override
    public void sortSelect() {
        for (int i = 0; i < pointer - 1; i++) {
            int min = i;

            for (int j = i + 1; j < pointer; j++) {
                if (data[j].compareTo(data[min]) < 0)
                    min = j;
            }

            if (min != i)
                exchange(i, min);
        }
    }

    @Override
    public void sortInsert() {
        for (int i = 1; i < pointer; i++) {
            int lastOfSorted = i - 1;

            while (lastOfSorted >= 0 && data[lastOfSorted + 1].compareTo(data[lastOfSorted]) < 0) {
                exchange(lastOfSorted + 1, lastOfSorted--);
            }
        }
    }

    private void grow() {
        if (pointer == data.length - 1) {
            data = Arrays.copyOf(data, data.length << 1);
        }
    }

    private int getIndex(E element) {
        for (int i = 0; i < pointer; i++) {
            if (data[i].equals(element))
                return i;
        }

        return INCORRECT_INDEX;
    }

    private void exchange(int j, int i) {
        E buffer = data[j];
        data[j] = data[i];
        data[i] = buffer;
    }
}
