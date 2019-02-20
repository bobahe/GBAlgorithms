package ru.bobahe.algorithms.ds;

public class BasicDeque<E> extends BasicQueue<E> implements Deque<E> {
    public BasicDeque() {
        super();
    }

    public BasicDeque(int size) {
        super(size);
    }

    @Override
    public void insertLeft(E element) {
        if (isFull())
            throw new IndexOutOfBoundsException("Queue is full. Adding is blocked.");

        data[start--] = element;
        if (start < 0)
            start = data.length - 1;
        size++;
    }

    @Override
    public void insertRight(E element) {
        push(element);
    }

    @Override
    public E removeLeft() {
        return remove();
    }

    @Override
    public E removeRight() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Queue is empty. There is nothing to dequeue.");

        if (--end < 0)
            end = data.length - 1;

        E result = data[end];
        size--;

        return result;
    }
}
