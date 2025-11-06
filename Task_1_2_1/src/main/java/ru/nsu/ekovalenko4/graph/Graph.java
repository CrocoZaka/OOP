package ru.nsu.ekovalenko4.graph;

import java.io.IOException;
import java.util.List;

/**
 * Interface for a generic directed graph structure with basic operations.
 * Implementations may use different internal representations.
 */
public interface Graph<T> {
    void addVertex(T vertex);

    void removeVertex(T vertex);

    void addEdge(T from, T to);

    void removeEdge(T from, T to);

    List<T> getVertices();

    List<T> getNeighbors(T vertex);

    void readFromFile(String filename) throws IOException;
}
