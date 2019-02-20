package ru.bobahe.algorithms.ds;

public class BasicQueue<E> implements Queue<E> {
    private static final int DEFAULT_SIZE = 8;
    E[] data;
    int size;

    int start;
    int end;

    public BasicQueue() {
        this(DEFAULT_SIZE);
    }

    public BasicQueue(int size) {
        data = (E[]) new Object[size];
    }

    @Override
    public void push(E element) {
        if (isFull())
            throw new IndexOutOfBoundsException("Queue is full. Adding is blocked.");

        data[end++] = element;
        end %= data.length;
        size++;
    }

    @Override
    public E remove() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Queue is empty. There is nothing to dequeue.");

        E result = data[start++];
        start %= data.length;
        size--;

        return result;
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;

        return data[start];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public int size() {
        return size;
    }
}
