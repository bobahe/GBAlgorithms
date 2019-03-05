package ru.bobahe.algorithms.simpletree;

import java.util.ArrayList;
import java.util.List;

public class TestApp {
    public static final int FOREST_SIZE = 20;

    public static void main(String[] args) {
        List<SimpleTree<Integer>> forest = new ArrayList<>(FOREST_SIZE);

        for (int i = 0; i < FOREST_SIZE; i++) {
            SimpleTree<Integer> t = new SimpleTree<>();
            forest.add(t);

            for (int j = 0; j < 16; j++) {
                t.add((int) (Math.random() * 40 - 20));
            }
        }

        showBalancedPercentage(forest);
    }

    private static void showBalancedPercentage(List<? extends SimpleTree> forest) {
        int bstCount = 0;

        for (SimpleTree t : forest) {
            bstCount += t.isBalanced() ? 1 : 0;
        }

        System.out.println(bstCount * 100 / forest.size() + "% сгенерированных деревьев являтся сбаланисрованными (" +
                bstCount + " из " + forest.size() + ").");
    }
}
