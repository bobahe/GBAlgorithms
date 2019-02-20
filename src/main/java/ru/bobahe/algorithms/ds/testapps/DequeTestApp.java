package ru.bobahe.algorithms.ds.testapps;

import ru.bobahe.algorithms.ds.BasicDeque;
import ru.bobahe.algorithms.ds.Deque;

public class DequeTestApp {
    public static void main(String[] args) {
        Deque<Integer> deque = new BasicDeque<>();
        deque.insertRight(1);
        deque.removeLeft();
        deque.insertLeft(1);
        deque.insertRight(2);
        deque.insertRight(3);

        System.out.println(deque.removeLeft() +  " " + deque.removeRight());
        System.out.println(deque.removeLeft());
        System.out.println(deque.remove());
    }
}
