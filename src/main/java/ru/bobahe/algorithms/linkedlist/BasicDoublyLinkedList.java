package ru.bobahe.algorithms.linkedlist;

public class BasicDoublyLinkedList<E> extends BasicSinglyLinkedList<E> implements DoublyLinkedList<E> {
    private Node<E> lastNode;

    @Override
    public void insertLeft(E value) {
        insert(value);

        if (size == 1) {
            lastNode = firstNode;
        }
    }

    @Override
    public void insertRight(E value) {
        Node<E> newNode = new Node<>(value, null);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            lastNode.nextNode = newNode;
        }

        lastNode = newNode;
        size++;
    }

    @Override
    public E removeLeft() {
        E value = remove();

        if (isEmpty()) {
            lastNode = firstNode;
        }

        return value;
    }

    @Override
    public E remove(E value) {
        Node<E> currentNode = firstNode;
        Node<E> previousNode = null;

        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                if (previousNode == null) {
                    firstNode = currentNode.nextNode;
                } else {
                    previousNode.nextNode = currentNode.nextNode;

                    if (currentNode.nextNode == null) {
                        lastNode = previousNode;
                    }
                }

                size--;

                return currentNode.value;
            }

            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }

        return null;
    }

    @Override
    public E getLastElementValue() {
        return lastNode.value;
    }
}
