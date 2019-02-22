package ru.bobahe.algorithms.linkedlist;

public interface DoublyLinkedList<E> extends SinglyLinkedList<E> {
    void insertLeft(E value);
    void insertRight(E value);

    E getLastElementValue();

    E removeLeft();
}
