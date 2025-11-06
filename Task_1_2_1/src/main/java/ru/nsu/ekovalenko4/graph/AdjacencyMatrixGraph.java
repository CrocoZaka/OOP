package ru.nsu.ekovalenko4.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Implementation of a Graph using adjacency matrix.
 * Supports dynamic resizing of the matrix as vertices are added.
 */
public class AdjacencyMatrixGraph<T> implements Graph<T> {

    private final HashMap<T, Integer> vertexToIndex;
    private final HashMap<Integer, T> indexToVertex;
    private boolean[][] matrix;

    private static final int INITIAL_CAPACITY = 8;

    public AdjacencyMatrixGraph() {
        vertexToIndex = new HashMap<>();
        indexToVertex = new HashMap<>();
        matrix = new boolean[INITIAL_CAPACITY][INITIAL_CAPACITY];
    }

    @Override
    public void addVertex(T vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return;
        }
        int newIndex = vertexToIndex.size();
        if (newIndex >= matrix.length) {
            expandMatrix();
        }
        vertexToIndex.put(vertex, newIndex);
        indexToVertex.put(newIndex, vertex);
    }

    private void expandMatrix() {
        int newCapacity = Math.max(1, matrix.length * 2);
        boolean[][] newMatrix = new boolean[newCapacity][newCapacity];
        for (int i = 0; i < vertexToIndex.size(); i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, vertexToIndex.size());
        }
        matrix = newMatrix;
    }

    @Override
    public void removeVertex(T vertex) {
        Integer idx = vertexToIndex.get(vertex);
        if (idx == null) {
            return;
        }

        int lastIndex = matrix.length - 1;
        if (idx != lastIndex) {
            T lastVertex = indexToVertex.get(lastIndex);

            for (int j = 0; j < indexToVertex.size(); j++) {
                boolean tmp = matrix[idx][j];
                matrix[idx][j] = matrix[lastIndex][j];
                matrix[lastIndex][j] = tmp;
            }

            for (int i = 0; i < indexToVertex.size(); i++) {
                boolean tmp = matrix[i][idx];
                matrix[i][idx] = matrix[i][lastIndex];
                matrix[i][lastIndex] = tmp;
            }

            vertexToIndex.put(lastVertex, idx);
            indexToVertex.put(idx, lastVertex);
        }

        vertexToIndex.remove(vertex);
        indexToVertex.remove(lastIndex);

        for (int i = 0; i < vertexToIndex.size(); i++) {
            matrix[lastIndex][i] = false;
            matrix[i][lastIndex] = false;
        }
    }

    @Override
    public void addEdge(T from, T to) {
        if (!vertexToIndex.containsKey(from)) {
            addVertex(from);
        }
        if (!vertexToIndex.containsKey(to)) {
            addVertex(to);
        }
        matrix[vertexToIndex.get(from)][vertexToIndex.get(to)] = true;
    }

    @Override
    public void removeEdge(T from, T to) {
        if (vertexToIndex.containsKey(from) && vertexToIndex.containsKey(to)) {
            matrix[vertexToIndex.get(from)][vertexToIndex.get(to)] = false;
        }
    }

    @Override
    public ArrayList<T> getVertices() {
        return new ArrayList<>(vertexToIndex.keySet());
    }

    @Override
    public ArrayList<T> getNeighbors(T vertex) {
        ArrayList<T> neighbors = new ArrayList<>();
        if (!vertexToIndex.containsKey(vertex)) {
            return neighbors;
        }
        int vertexIdx = vertexToIndex.get(vertex);

        for (int i = 0; i < vertexToIndex.size(); i++) {
            if (matrix[vertexIdx][i]) {
                neighbors.add(indexToVertex.get(i));
            }
        }
        return neighbors;
    }

    @Override
    public void readFromFile(String filename) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                String fromStr = sc.next();
                String toStr = sc.next();
                @SuppressWarnings("unchecked")
                T from = (T) fromStr;
                @SuppressWarnings("unchecked")
                T to = (T) toStr;
                addEdge(from, to);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AdjacencyMatrixGraph<?> g)) {
            return false;
        }
        return vertexToIndex.equals(g.vertexToIndex) && Arrays.deepEquals(matrix, g.matrix);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not implemented for Graph class.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<T> vertices = new ArrayList<>(vertexToIndex.keySet());
        sb.append("     ");
        for (T v : vertices) {
            sb.append(v).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i)).append(": ");
            for (int j = 0; j < vertices.size(); j++) {
                sb.append(matrix[i][j] ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

