package ru.bobahe.algorithms.ds;

import ru.bobahe.algorithms.linkedlist.BasicDoublyLinkedList;
import ru.bobahe.algorithms.linkedlist.DoublyLinkedList;

public class BasicLinkedQueue<E> implements Queue<E> {
    private final DoublyLinkedList<E> data;

    public BasicLinkedQueue() {
        this.data = new BasicDoublyLinkedList<>();
    }

    @Override
    public void push(E element) {
        data.insertRight(element);
    }

    @Override
    public E remove() {
        return data.remove();
    }

    @Override
    public E peek() {
        return data.getLastElementValue();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int size() {
        return data.size();
    }
}
