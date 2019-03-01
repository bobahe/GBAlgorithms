package ru.bobahe.algorithms.recursion.backpack;

public class Item {
    private String name;
    private int weight;
    private int cost;

    public Item(String name, int weight, int cost) {
        this(weight, cost);
        this.name = name;
    }

    public Item(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (name != null) {
            result.append(name).append(": ");
        }

        result.append(weight).append(" (").append(cost).append(")");

        return result.toString();
    }
}
