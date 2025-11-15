package ru.nsu.ekovalenko4.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Topological sorting implementation class.
 * The class provides a sorting method returning a list of vertices in topologically sorted order.
 */
public class TopologicalSort {
    /**
     * Topological sorting method.
     */
    public static <T> List<T> topologicalSort(Graph<T> graph) {
        HashMap<T, Integer> indegree = new HashMap<>();
        List<T> vertices = graph.getVertices();
        for (T v : vertices) {
            indegree.put(v, 0);
        }
        for (T v : vertices) {
            for (T n : graph.getNeighbors(v)) {
                indegree.put(n, indegree.get(n) + 1);
            }
        }

        List<T> queue = new ArrayList<>();
        for (T v : vertices) {
            if (indegree.get(v) == 0) {
                queue.add(v);
            }
        }

        List<T> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            T v = queue.remove(0);
            result.add(v);

            for (T n : graph.getNeighbors(v)) {
                indegree.put(n, indegree.get(n) - 1);
                if (indegree.get(n) == 0) {
                    queue.add(n);
                }
            }
        }

        if (result.size() != vertices.size()) {
            throw new IllegalArgumentException("Graph contains a cycle");
        }

        return result;
    }
}
