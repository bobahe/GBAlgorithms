package ru.bobahe.algorithms.graph;

import java.util.*;
import java.util.stream.Stream;

public class Graph {
    /**
     * List of vertices
     */
    private List<Vertex> vertices;

    /**
     * Matrix of vertices edges
     */
    private boolean[][] linksMatrix;

    /**
     * Construct an empty graph with specified capacity
     * @param capacity the capacity of graph
     */
    public Graph(int capacity) {
        linksMatrix = new boolean[capacity][capacity];
        vertices = new ArrayList<>(capacity);
    }

    /**
     * Adds vertex with label to graph
     * @param label label that represents vertex
     */
    public void addVertex(String label) {
        if (findVertex(label) != -1) {
            throw new IllegalStateException("Graph has got vertex with the same label already.");
        }

        if (vertices.size() >= linksMatrix.length) {
            throw new IllegalStateException("Maximum capacity is exceeded.");
        }

        vertices.add(new Vertex(label));
    }

    /**
     * Creates edges between vertex with label equals start and vertices with labels of finish.
     * Calls createEdge() method for each pair of start and next one from finish array.
     * @param start label of start vertex
     * @param finish labels of finish vertices
     */
    public void createEdges(String start, String... finish) {
        if (!Stream.of(finish).allMatch(label -> findVertex(label) != -1) || findVertex(start) == -1) {
            throw new IllegalArgumentException("There are no such vertices in graph.");
        }

        Stream.of(finish).forEach(finishLabel -> createEdge(start, finishLabel));
    }

    /**
     * Performs "depth first search" traverse
     * @param start label of start vertex
     */
    public void dfsTraverse(String start) {
        int vertexIndex = findVertex(start);

        if (vertexIndex == -1) {
            throw new IllegalArgumentException("There is no vertex labeled " + start + " in graph.");
        }

        Stack<Vertex> vertexStack = new Stack<>();
        vertexStack.push(vertices.get(vertexIndex));

        do {
            if (!vertexStack.peek().wasVisited) {
                display(vertexStack.peek());
                markAsVisited(vertexStack.peek());
            }
            Vertex next = getUnvisitedNeighbor(vertexStack.peek());
            if (next != null) {
                vertexStack.push(next);
            } else {
                vertexStack.pop();
            }
        } while (!vertexStack.isEmpty());
    }

    /**
     * Performs "breadth first search" traverse
     * @param start label of start vertex
     */
    public void bfsTraverse(String start) {
        int vertexIndex = findVertex(start);

        if (vertexIndex == -1) {
            throw new IllegalArgumentException("There is no vertex labeled " + start + " in graph.");
        }

        Queue<Vertex> vertexQueue = new LinkedList<>();
        vertexQueue.add(vertices.get(vertexIndex));
        display(vertexQueue.peek());
        markAsVisited(vertexQueue.peek());

        do {
            Vertex next = getUnvisitedNeighbor(vertexQueue.peek());
            if (next != null) {
                vertexQueue.add(next);
                display(next);
                markAsVisited(next);
            } else {
                vertexQueue.remove();
            }
        } while (!vertexQueue.isEmpty());
    }

    /**
     * Searches shortest path from vertex with label equals start to vertex with label equals destination.
     * @param start vertex label
     * @param destination vertex label
     */
    public void bfs(String start, String destination) {
        if (findVertex(start) == -1 || findVertex(destination) == -1) {
            throw new IllegalArgumentException("There are no such vertices in graph.");
        }

        Queue<Vertex> vertexQueue = new LinkedList<>();
        vertexQueue.add(vertices.get(findVertex(start)));
        markAsVisited(vertexQueue.peek());

        do {
            Vertex next = getUnvisitedNeighbor(vertexQueue.peek());
            if (next != null) {
                next.parents.add(vertexQueue.peek());

                if (next.label.equals(destination)) {
                    printPath(next);
                    break;
                }

                vertexQueue.add(next);
                markAsVisited(next);
            } else {
                vertexQueue.remove();
            }
        } while (!vertexQueue.isEmpty());
    }

    /**
     * Builds and displays shortest path
     * @param v vertex
     */
    private void printPath(Vertex v) {
        if (v.parents.isEmpty()) {
            display(v);
            return;
        }

        printPath(v.parents.get(v.parents.size() - 1));
        display(v);
    }

    /**
     * Print vertex label to standard output
     * @param v vertex
     */
    private void display(Vertex v) {
        System.out.print(v.label + " ");
    }

    /**
     * Finds near unvisited vertex
     * @param v start vertex
     * @return first unvisited linked vertex or null
     */
    private Vertex getUnvisitedNeighbor(Vertex v) {
        for (int i = 0; i < vertices.size(); i++) {
            if (linksMatrix[findVertex(v.label)][i] && !vertices.get(i).wasVisited) {
                return vertices.get(i);
            }
        }

        return null;
    }

    /**
     * Marks specified vertex as visited
     * @param v vertex
     */
    private void markAsVisited(Vertex v) {
        v.wasVisited = true;
    }

    /**
     * Creates an edge between vertex with label equals start and vertex with label equals finish
     * @param start label of start vertex
     * @param finish label of finish vertex
     */
    private void createEdge(String start, String finish) {
        int startIndex = findVertex(start);
        int finishIndex = findVertex(finish);

        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("There are no such vertices in graph.");
        }

        linksMatrix[startIndex][finishIndex] = true;
        linksMatrix[finishIndex][startIndex] = true;
    }

    /**
     * Finds index of vertex with specified label
     * @param label label of vertex
     * @return found vertex index or -1 if vertex wasn't found
     */
    private int findVertex(String label) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).label.equals(label)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Represents vertex for simple graph
     */
    private static class Vertex {
        String label;
        boolean wasVisited;
        List<Vertex> parents = new ArrayList<>();

        /**
         * Constructs vertex object with specified label
         * @param label vertex label
         */
        Vertex(String label) {
            this.label = label;
        }
    }
}
