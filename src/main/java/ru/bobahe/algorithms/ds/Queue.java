package ru.bobahe.algorithms.ds;

public interface Queue<E> {
    void push(E element);
    E remove();
    E peek();

    boolean isEmpty();
    boolean isFull();
    int size();
}
