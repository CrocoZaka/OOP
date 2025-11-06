package ru.nsu.ekovalenko4.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nsu.ekovalenko4.graph.TopologicalSort.topologicalSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for verifying correctness of all Graph implementations.
 */
public class GeneralGraphTest {

    private final List<Graph<String>> graphs = List.of(
            new AdjacencyMatrixGraph<>(),
            new IncidenceMatrixGraph<>(),
            new AdjacencyListGraph<>()
    );

    private Graph<String> newInstance(Graph<String> g) {
        Graph<String> res = new AdjacencyMatrixGraph<>();
        if (g instanceof IncidenceMatrixGraph<?>) {
            res = new IncidenceMatrixGraph<>();
        }
        if (g instanceof AdjacencyListGraph<?>) {
            res = new AdjacencyListGraph<>();
        }
        return res;
    }

    @Test
    void testAddAndRemoveVertices() {
        for (Graph<String> g : graphs) {
            g.addVertex("A");
            g.addVertex("B");
            assertTrue(g.getNeighbors("A").isEmpty());
            assertTrue(g.getNeighbors("B").isEmpty());

            g.addEdge("A", "B");
            assertTrue(g.getNeighbors("A").contains("B"));

            g.removeVertex("B");
            assertFalse(g.getNeighbors("A").contains("B"));
            assertTrue(g.getNeighbors("A").isEmpty());
        }
    }

    @Test
    void testAddDuplicateVertex() {
        for (Graph<String> g : graphs) {
            g.addVertex("X");
            g.addVertex("X");
            g.addEdge("X", "X");
            assertTrue(g.getNeighbors("X").contains("X"));
        }
    }

    @Test
    void testMatrixExpansion() {
        for (Graph<String> g : graphs) {
            for (int i = 0; i < 20; i++) {
                String v = "V" + i;
                g.addVertex(v);
                if (i > 0) {
                    g.addEdge("V" + (i - 1), v);
                }
            }

            List<String> topo = topologicalSort(g);
            assertEquals(20, topo.size());
            assertTrue(topo.indexOf("V0") < topo.indexOf("V19"));
        }
    }

    @Test
    void testRemoveNonexistentVertex() {
        for (Graph<String> g : graphs) {
            g.addVertex("A");
            g.addEdge("A", "A");
            g.removeVertex("Z");
            assertTrue(g.getNeighbors("A").contains("A"));
        }
    }

    @Test
    void testAddAndRemoveEdges() {
        for (Graph<String> g : graphs) {
            g.addEdge("A", "B");
            g.addEdge("B", "C");
            assertTrue(g.getNeighbors("A").contains("B"));
            assertTrue(g.getNeighbors("B").contains("C"));

            g.removeEdge("B", "A");
            assertTrue(g.getNeighbors("A").contains("B"));

            g.removeEdge("A", "B");
            assertFalse(g.getNeighbors("A").contains("B"));
        }
    }

    @Test
    void testEquals() {
        for (Graph<String> g : graphs) {
            g.addEdge("A", "B");
            g.addEdge("B", "C");

            Graph<String> same = newInstance(g);
            same.addEdge("A", "B");
            same.addEdge("B", "C");

            Graph<String> diff = newInstance(g);
            diff.addEdge("A", "C");

            assertEquals(g, same);
            assertNotEquals(g, diff);
        }
    }

    @Test
    void testEqualsDifferentTypesAndImplementations() {
        Graph<Integer> intGraph = new AdjacencyMatrixGraph<>();
        Graph<String> stringGraph = new AdjacencyMatrixGraph<>();
        intGraph.addEdge(1, 2);
        stringGraph.addEdge("1", "2");
        assertNotEquals(intGraph, stringGraph);

        Graph<String> matrixGraph = new AdjacencyMatrixGraph<>();
        Graph<String> listGraph = new AdjacencyListGraph<>();
        matrixGraph.addEdge("A", "B");
        listGraph.addEdge("A", "B");
        assertNotEquals(matrixGraph, listGraph);
    }

    @Test
    void testTopologicalSortOrder() {
        for (Graph<String> g : graphs) {
            g.addEdge("A", "B");
            g.addEdge("B", "C");
            g.addEdge("A", "C");
            List<String> order = topologicalSort(g);
            assertTrue(order.indexOf("A") < order.indexOf("B"));
            assertTrue(order.indexOf("B") < order.indexOf("C"));
        }
    }

    @Test
    void testReadFromFileThrowsWhenNotFound() {
        for (Graph<String> g : graphs) {
            assertThrows(FileNotFoundException.class, () -> g.readFromFile("no_file.txt"));
        }
    }

    @Test
    void testGetNeighborsEmpty() {
        for (Graph<String> g : graphs) {
            assertTrue(g.getNeighbors("Z").isEmpty());
        }
    }

    @Test
    void testReadFromFile() throws Exception {
        File tempFile = Files.createTempFile("graph_test", ".txt").toFile();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("A B\n");
            writer.write("B C\n");
            writer.write("C D\n");
        }

        for (Graph<String> g : graphs) {
            g.readFromFile(tempFile.getAbsolutePath());

            assertTrue(g.getNeighbors("A").contains("B"));
            assertTrue(g.getNeighbors("B").contains("C"));
            assertTrue(g.getNeighbors("C").contains("D"));

            List<String> topo = topologicalSort(g);
            assertTrue(topo.indexOf("A") < topo.indexOf("D"));

            String str = g.toString();
            assertTrue(str.contains("A") && str.contains("B") && str.contains("C") && str.contains("D"));
        }

        assertTrue(tempFile.delete());
    }

}
