package ru.bobahe;

public interface BasicList<E extends Comparable<E>> {
    void add(E element);

    E get(int index);

    boolean contains(E element);
    int indexOf(E element);
    int binarySearch(E element);

    void remove(int index);
    void remove(E element);

    int size();

    void sortBubble();
    void sortSelect();
    void sortInsert();
}
