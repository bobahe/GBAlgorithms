package ru.bobahe.algorithms.linkedlist;

public interface SinglyLinkedList<E> extends Iterable<E> {
    void insert(E element);
    E remove();
    E remove(E element);

    boolean isEmpty();
    int size();
    E getFirstElementValue();

    boolean contains(E element);
}
