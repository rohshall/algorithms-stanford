package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author: mcvly
 * @since: 10/4/13
 */
public class GraphFactory {

    public enum Graphs {
        ADJACENCY,
        MATRIX
    }

    public static <T> DirectedGraph<T> createGraph(int vertices, int edges, Graphs type) {
        if (type == Graphs.ADJACENCY) {
            return new AdjacencyGraph<T>();
        } else {
            return new MatrixGraph<T>(vertices);
        }
    }

}
