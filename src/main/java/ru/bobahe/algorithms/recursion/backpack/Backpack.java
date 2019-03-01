package ru.bobahe.algorithms.recursion.backpack;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
    private int maxWeight;
    private List<Item> bestSet;
    private int bestCost;

    public Backpack(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Item> getBestSet(List<Item> items) {
        testCombinations(items);

        return bestSet;
    }

    private void testCombinations(List<Item> items) {
        // базовый случай
        if (items.size() > 0) {
            compareSet(items);
        }

        // рекурсивные случаи
        for (int i = 0; i < items.size(); i++) {
            List<Item> newSet = new ArrayList<>(items);
            newSet.remove(i);
            testCombinations(newSet);
        }
    }

    private void compareSet(List<Item> items) {
        int tempCost = getSummaryCost(items);

        if (getSummaryWeight(items) <= maxWeight && tempCost > bestCost) {
            bestSet = items;
            bestCost = tempCost;
        }
    }

    private int getSummaryCost(List<Item> items) {
        // попытки начать понимать Stream API
        return items.stream().mapToInt(Item::getCost).sum();
    }

    private int getSummaryWeight(List<Item> items) {
        return items.stream().mapToInt(Item::getWeight).sum();
    }
}
