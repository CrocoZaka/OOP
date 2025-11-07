package ru.nsu.ekovalenko4.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

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

    /**
     * Reads a graph from a text file.
     * The file must contain pairs of vertex identifiers (from-to)
     * separated by whitespace, one edge per line.
     */
    default void readFromFile(String filename, Function<String, T> parser) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                String fromStr = sc.next();
                String toStr = sc.next();
                T from = parser.apply(fromStr);
                T to = parser.apply(toStr);
                addEdge(from, to);
            }
        }
    }
}
