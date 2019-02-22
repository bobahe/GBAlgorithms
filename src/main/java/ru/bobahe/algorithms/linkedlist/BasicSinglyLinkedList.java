package ru.bobahe.algorithms.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BasicSinglyLinkedList<E> implements SinglyLinkedList<E> {
    Node<E> firstNode;
    int size;

    @Override
    public void insert(E value) {
        firstNode = new Node<>(value, firstNode);
        size++;
    }

    @Override
    public E remove() {
        if (size > 0) {
            Node<E> nodeToRemove = firstNode;

            firstNode = nodeToRemove.nextNode;
            size--;

            return nodeToRemove.value;
        }

        return null;
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
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E getFirstElementValue() {
        return firstNode.value;
    }

    public Node<E> getFirstNode() {
        return firstNode;
    }

    @Override
    public boolean contains(E value) {
        Node<E> currentNode = firstNode;

        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return true;
            }

            currentNode = currentNode.nextNode;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<E> currentNode = firstNode;

        result.append("{");

        if (size == 0) {
            return result.append("}").toString();
        }

        while (currentNode != null) {
            result.append(currentNode.value).append(", ");

            currentNode = currentNode.nextNode;
        }

        result.replace(result.length() - 2, result.length(), "}");

        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new BasicIterator();
    }

    // Внутренний класс для узла списка

    // В различных примерах такие внутренние классы делают статическими, но я так и не понял для чего :(
    // В моем случае Node будут создаваться именно тогда, когда есть объект верхнего класса, а следовательно ссылка на
    // такой объект будет доступна. Может быть для того, чтобы не было доступа к нестатическим полям и методам
    // верхнего класса?
    static class Node<E> {
        E value;
        Node<E> nextNode;

        Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        @Override
        public boolean equals(Object o) {
            // IntelliJ Idea посоветовала написать так. Я только добавил проверку на класс перед кастом o.
            // Правильно ли я понимаю, что проверка будет проходить слева направо и, если классы разные,
            // то до каста просто дело не дойдет? Ведь нет смысла проверять все &&, если, например,
            // первый уже дал false?
            // Подскажите, пожалуйста, правильно ли это. Спасибо!
            return this == o || o != null && getClass() == o.getClass() && value.equals(((Node<?>) o).value);
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    // First attempted Iterator class
    class BasicIterator implements Iterator<E> {
        private Node<E> cursor;
        private Node<E> previous;

        // Судя по javadoc, мне надо как-то запоминать вызовы next() и remove(), чтобы кидать нужные исключения
        private boolean nextWasCalled;
        private boolean removeWasCalled;

        @Override
        public boolean hasNext() {
            if (cursor == null) {
                return getFirstNode() != null;
            }

            return cursor.nextNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (removeWasCalled) {
                removeWasCalled = false;
            }

            nextWasCalled = true;

            previous = cursor;

            if (cursor == null) {
                cursor = getFirstNode();
            } else {
                cursor = cursor.nextNode;
            }

            return cursor.value;
        }

        @Override
        public void remove() {
            if (removeWasCalled || !nextWasCalled) {
                throw new IllegalStateException(
                        "Нельзя вызывать метод remove(), если он только что был вызван " +
                                "или если еще ни разу не вызывался метод next()."
                );
            }

            if (previous == null) {
                firstNode = cursor.nextNode;
                cursor = null;
            } else {
                previous.nextNode = cursor.nextNode;
            }

            size--;

            removeWasCalled = true;
        }
    }
}
