package ru.bobahe.algorithms.simpletree;

public interface Tree<E extends Comparable<E>> {
    void add(E element);

    boolean remove(E element);

    boolean contains(E element);
    boolean isEmpty();
    int size();

    int getHeight();
    boolean isBalanced();

    void display();
}
