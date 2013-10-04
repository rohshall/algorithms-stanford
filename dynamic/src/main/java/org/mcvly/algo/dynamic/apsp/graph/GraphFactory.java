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

    public static DirectedGraph createGraph(int vertices, int edges, Graphs type) {
        if (type == Graphs.ADJACENCY) {
            return new AdjacencyGraph(vertices, edges);
        } else {
            return new MatrixGraph(vertices);
        }
    }

}
