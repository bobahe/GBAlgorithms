package ru.bobahe.algorithms.ds;

public interface Stack<E> {
    void push(E element);
    E peek();
    E pop();

    boolean isEmpty();
    boolean isFull();
    int size();
}
