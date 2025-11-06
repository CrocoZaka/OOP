package ru.nsu.ekovalenko4.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Implementation of a Graph using incidence matrix.
 * The matrix stores 1 for the source vertex, -1 for the target vertex, and 0 otherwise.
 */
public class IncidenceMatrixGraph<T> implements Graph<T> {

    private static final int SOURCE = 1;
    private static final int TARGET = -1;
    private static final int INITIAL_CAPACITY = 8;

    private record Edge<T>(T from, T to) {
    }

    private final HashMap<T, Integer> vertexToIndex;
    private final HashMap<Integer, T> indexToVertex;
    private final ArrayList<Edge<T>> edges;
    private int[][] matrix;

    public IncidenceMatrixGraph() {
        vertexToIndex = new HashMap<>();
        indexToVertex = new HashMap<>();
        edges = new ArrayList<>();
        matrix = new int[INITIAL_CAPACITY][INITIAL_CAPACITY];
    }

    @Override
    public void addVertex(T vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return;
        }

        int idx = vertexToIndex.size();
        if (idx >= matrix.length) {
            expandRows();
        }
        vertexToIndex.put(vertex, idx);
        indexToVertex.put(idx, vertex);
    }

    private void expandRows() {
        int newCap = matrix.length * 2;
        int[][] newMatrix = new int[newCap][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }
        matrix = newMatrix;
    }

    private void expandCols() {
        int newCap = matrix[0].length * 2;
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = Arrays.copyOf(matrix[i], newCap);
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

        if (edges.size() >= matrix[0].length) {
            expandCols();
        }

        final int fromIdx = vertexToIndex.get(from);
        final int toIdx = vertexToIndex.get(to);

        Edge<T> edge = new Edge<>(from, to);
        edges.add(edge);

        int col = edges.size() - 1;
        for (int i = 0; i < vertexToIndex.size(); i++) {
            matrix[i][col] = 0;
        }
        matrix[fromIdx][col] = SOURCE;
        matrix[toIdx][col] = TARGET;
    }

    @Override
    public void removeEdge(T from, T to) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> e = edges.get(i);
            if (e.from.equals(from) && e.to.equals(to)) {
                edges.remove(i);
                for (int r = 0; r < vertexToIndex.size(); r++) {
                    for (int c = i; c < edges.size(); c++) {
                        matrix[r][c] = matrix[r][c + 1];
                    }
                    matrix[r][edges.size()] = 0;
                }
                return;
            }
        }
    }

    @Override
    public void removeVertex(T vertex) {
        Integer idx = vertexToIndex.get(vertex);
        if (idx == null) {
            return;
        }

        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge<T> e = edges.get(i);
            if (e.from.equals(vertex) || e.to.equals(vertex)) {
                removeEdge(e.from, e.to);
            }
        }

        int lastIdx = vertexToIndex.size() - 1;
        if (idx != lastIdx) {
            T lastVertex = indexToVertex.get(lastIdx);

            int[] tmp = matrix[idx];
            matrix[idx] = matrix[lastIdx];
            matrix[lastIdx] = tmp;

            vertexToIndex.put(lastVertex, idx);
            indexToVertex.put(idx, lastVertex);
        }

        vertexToIndex.remove(vertex);
        indexToVertex.remove(lastIdx);
    }

    @Override
    public ArrayList<T> getVertices() {
        return new ArrayList<>(vertexToIndex.keySet());
    }

    @Override
    public ArrayList<T> getNeighbors(T vertex) {
        ArrayList<T> res = new ArrayList<>();
        Integer idx = vertexToIndex.get(vertex);
        if (idx == null) {
            return res;
        }

        for (int i = 0; i < edges.size(); i++) {
            if (matrix[idx][i] == SOURCE) {
                res.add(edges.get(i).to);
            }
        }
        return res;
    }

    @Override
    public void readFromFile(String filename) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                String a = sc.next();
                String b = sc.next();
                @SuppressWarnings("unchecked")
                T from = (T) a;
                @SuppressWarnings("unchecked")
                T to = (T) b;
                addEdge(from, to);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IncidenceMatrixGraph<?> g)) return false;
        return vertexToIndex.equals(g.vertexToIndex)
                && edges.equals(g.edges)
                && Arrays.deepEquals(matrix, g.matrix);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not implemented for Graph class.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        final ArrayList<T> vertices = new ArrayList<>(vertexToIndex.keySet());
        sb.append("     ");
        for (int j = 0; j < edges.size(); j++) {
            sb.append("e").append(j).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(String.format("%5s: ", vertices.get(i)));
            for (int j = 0; j < edges.size(); j++) {
                sb.append(String.format("%2d ", matrix[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
