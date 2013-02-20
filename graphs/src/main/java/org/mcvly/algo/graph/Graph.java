package org.mcvly.algo.graph;

import java.util.List;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class Graph<V,E> {
    private List<V> vertices;
    private List<E> edges;

    public Graph() {}

    public Graph(List<V> vertices, List<E> edges) {
        this.edges = edges;
        this.vertices = vertices;
    }

    public List<V> getVertices() {
        return vertices;
    }

    public void setVertices(List<V> vertices) {
        this.vertices = vertices;
    }

    public List<E> getEdges() {
        return edges;
    }

    public void setEdges(List<E> edges) {
        this.edges = edges;
    }

    public void addVertex(V v) {
        vertices.add(v);
    }

    public void addEdge(E e) {
        edges.add(e);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Vertices: \n");
        for (V v : vertices) {
            b.append(v);
            b.append(" ");
        }
        b.append("\nEdges: \n");
        for (E e : edges) {
            b.append(e);
            b.append(" ");
        }
        return b.toString();
    }
}
