package ru.bobahe.algorithms;

import ru.bobahe.algorithms.graph.Graph;

public class TestApp {
    public static void main(String[] args) {
        Graph g = new Graph(10);

        g.addVertex("Москва");
        g.addVertex("Тула");
        g.addVertex("Липецк");
        g.addVertex("Воронеж");
        g.addVertex("Рязань");
        g.addVertex("Тамбов");
        g.addVertex("Саратов");
        g.addVertex("Калуга");
        g.addVertex("Орел");
        g.addVertex("Курск");

        g.createEdges("Москва", "Тула", "Рязань", "Калуга");
        g.createEdges("Воронеж", "Липецк", "Саратов", "Курск");
//        g.createEdges("Воронеж", "Липецк", "Саратов", "Курск", "Калуга");
        g.createEdges("Тула", "Липецк");
        g.createEdges("Рязань", "Тамбов");
        g.createEdges("Тамбов", "Саратов");
        g.createEdges("Калуга", "Орел");
        g.createEdges("Курск", "Орел");

        g.bfs("Москва", "Воронеж");
    }
}
