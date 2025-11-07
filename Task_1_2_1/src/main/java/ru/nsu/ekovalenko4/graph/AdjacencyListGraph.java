package ru.nsu.ekovalenko4.graph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of a Graph using adjacency list.
 */
public class AdjacencyListGraph<T> implements Graph<T> {

    private final HashMap<T, ArrayList<T>> adjList;

    public AdjacencyListGraph() {
        adjList = new HashMap<>();
    }

    @Override
    public void addVertex(T vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(T vertex) {
        adjList.remove(vertex);
        for (ArrayList<T> neighbors : adjList.values()) {
            neighbors.remove(vertex);
        }
    }

    @Override
    public void addEdge(T from, T to) {
        addVertex(from);
        addVertex(to);
        ArrayList<T> neighbors = adjList.get(from);
        if (!neighbors.contains(to)) {
            neighbors.add(to);
        }
    }

    @Override
    public void removeEdge(T from, T to) {
        ArrayList<T> neighbors = adjList.get(from);
        neighbors.remove(to);
    }

    @Override
    public ArrayList<T> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }


    @Override
    public ArrayList<T> getNeighbors(T vertex) {
        if (!adjList.containsKey(vertex)) {
            return new ArrayList<>();
        }
        return adjList.get(vertex);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AdjacencyListGraph<?> g)) {
            return false;
        }
        return adjList.equals(g.adjList);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not implemented for Graph class.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Adjacency list:\n");
        for (var entry : adjList.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            sb.append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
