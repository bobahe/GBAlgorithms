package ru.bobahe.algorithms.ds;

public class BasicStack<E> implements Stack<E> {
    public static final int DEFAULT_SIZE = 16;

    private E[] data;
    private int size;

    public BasicStack() {
        this(DEFAULT_SIZE);
    }

    public BasicStack(E[] arr) {
        this(arr.length);
        System.arraycopy(arr, 0, data, 0, arr.length);
        size = data.length;
    }

    public BasicStack(int size) {
        data = (E[]) new Object[size];
    }

    @Override
    public void push(E element) {
        data[size++] = element;
    }

    @Override
    public E peek() {
        return data[size - 1];
    }

    @Override
    public E pop() {
        return data[--size];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == this.data.length;
    }

    @Override
    public int size() {
        return size;
    }
}
