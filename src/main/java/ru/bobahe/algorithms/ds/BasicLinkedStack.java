package ru.bobahe.algorithms.ds;

import ru.bobahe.algorithms.linkedlist.BasicSinglyLinkedList;
import ru.bobahe.algorithms.linkedlist.SinglyLinkedList;

public class BasicLinkedStack<E> implements Stack<E> {
    private final SinglyLinkedList<E> data;

    public BasicLinkedStack() {
        this.data = new BasicSinglyLinkedList<>();
    }

    @Override
    public void push(E element) {
        data.insert(element);
    }

    @Override
    public E peek() {
        return data.getFirstElementValue();
    }

    @Override
    public E pop() {
        return data.remove();
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
