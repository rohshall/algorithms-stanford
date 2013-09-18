package org.mcvly.algo.greedy.mst.core;

import java.util.Set;

/**
 * @author: mcvly
 * @since: 9/13/13
 */
public class UndirectedGraph {
    private Set<Vertex> vertices;
    private Set<UndirectedEdge> edges;

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Set<UndirectedEdge> getEdges() {
        return edges;
    }

    public void setEdges(Set<UndirectedEdge> edges) {
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "Vertices: " + vertices.toString() + "\n" + "Edges: " + edges.toString();
    }
}
