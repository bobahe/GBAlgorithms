package ru.bobahe.algorithms;

import ru.bobahe.algorithms.hashtable.ChainedHashtable;
import ru.bobahe.algorithms.hashtable.HashTable;

public class ChainedHashtableApp {
    public static void main(String[] args) {
        HashTable<String, String> t = new ChainedHashtable<>();

        t.put("Russia", "Saint Petersburg");
        t.put("Great Britain", "London");

        // should change capital of "Russia"
        t.put("Russia", "Moscow");

        // make a collision
        t.put("Riassu", "Test");

        // should print Test
        System.out.println(t.get("Riassu"));

        // should print null
        System.out.println(t.get("R"));

        // should print true and remove entry with key "Riassu"
        System.out.println(t.remove("Riassu"));

        // should print false and do nothing with data in hashtable
        System.out.println(t.remove("R"));
    }
}
