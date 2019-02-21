package ru.bobahe.algorithms;

import ru.bobahe.algorithms.linkedlist.BasicDoublyLinkedList;
import ru.bobahe.algorithms.linkedlist.DoublyLinkedList;

public class DoublyLinkedListApp {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new BasicDoublyLinkedList<>();

        list.insertRight(2);
        list.insertRight(1);

        System.out.println(list.toString() + " - " + list.size());

        list.remove(1);
        System.out.println(list.toString() + " - " + list.size());

        list.insertLeft(1);
        System.out.println(list.toString() + " - " + list.size());

        list.insertRight(3);
        System.out.println(list.toString() + " - " + list.size());

        list.remove();
        list.remove();
        list.remove();
        System.out.println(list.toString() + " - " + list.size());
    }
}
