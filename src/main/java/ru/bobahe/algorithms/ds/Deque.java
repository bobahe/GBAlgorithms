package ru.bobahe.algorithms.ds;

public interface Deque<E> extends Queue<E> {
    void insertLeft(E element);
    void insertRight(E element);
    E removeLeft();
    E removeRight();
}
