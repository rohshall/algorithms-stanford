package org.mcvly.algo.graph;

import java.util.List;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class Graph<T> {
    private List<T> vertices;
    private List<Edge<T>> edges;

    public Graph() {}

    public Graph(List<T> vertices, List<Edge<T>> edges) {
        this.edges = edges;
        this.vertices = vertices;
    }

    public List<T> getVertices() {
        return vertices;
    }

    public void setVertices(List<T> vertices) {
        this.vertices = vertices;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }

    public void addVertex(T v) {
        vertices.add(v);
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Vertices: \n");
        for (T v : vertices) {
            b.append(v);
            b.append(" ");
        }
        b.append("\nEdges: \n");
        for (Edge e : edges) {
            b.append(e);
            b.append(" ");
        }
        return b.toString();
    }
}
