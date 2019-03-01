package ru.bobahe.algorithms.recursion.backpack;

import java.util.Arrays;

public class BackpackApp {
    public static void main(String[] args) {
        Backpack backpack = new Backpack(8);
        System.out.println(backpack.getBestSet(Arrays.asList(
                new Item("Книга", 2, 20),
                new Item("Кошелек", 1, 50),
                new Item("Ноутбук", 5, 100),
                new Item("Сигареты", 1, 10),
                new Item("Ключи", 1, 70)
        )));
    }
}
