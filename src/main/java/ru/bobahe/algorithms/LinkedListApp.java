package ru.bobahe.algorithms;

import ru.bobahe.algorithms.linkedlist.BasicSinglyLinkedList;
import ru.bobahe.algorithms.linkedlist.SinglyLinkedList;

import java.util.Iterator;

public class LinkedListApp {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new BasicSinglyLinkedList<>();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);

        list.remove(2);
        list.remove(222);
        list.remove();

        System.out.println(list);
        System.out.println(list.size());

        list.remove();
        System.out.println(list);

        list.remove();
        list.remove();

        System.out.println(list);

        System.out.println("=== Testing empty list iterator ===");
        for (Integer elem : list) {
            System.out.println(elem);
        }

        System.out.println("=== Testing list with nodes iterator ===");
        list.insert(5);
        list.insert(4);
        list.insert(3);
        list.insert(2);
        list.insert(1);

        for (Integer elem : list) {
            System.out.println(elem);
        }

        System.out.println("=== Testing remove node with iterator ===");

        Iterator<Integer> iter = list.iterator();
        while(iter.hasNext()) {
            if (iter.next() == 3) {
                iter.remove();
            }
//            iter.next();
//            iter.remove();
        }

        for (Integer elem : list) {
            System.out.println(elem);
        }
    }
}
