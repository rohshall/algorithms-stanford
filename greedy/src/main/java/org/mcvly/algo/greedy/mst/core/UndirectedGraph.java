package org.mcvly.algo.greedy.mst.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: mcvly
 * @since: 9/13/13
 */
public class UndirectedGraph {
    private Map<Vertex, Set<UndirectedEdge>> vertices = new HashMap<>();
    private Set<UndirectedEdge> edges = new HashSet<>();

    public void addEdge(UndirectedEdge edge) {
        edges.add(edge);

        Vertex v1 = edge.getFirstVertex();
        Vertex v2 = edge.getSecondVertex();

        if (!vertices.containsKey(v1)) {
            vertices.put(v1, new HashSet<>(Arrays.asList(edge)));
        } else {
            vertices.get(v1).add(edge);
        }

        if (!vertices.containsKey(v2)) {
            vertices.put(v2, new HashSet<>(Arrays.asList(edge)));
        } else {
            vertices.get(v2).add(edge);
        }
    }

    public Set<Vertex> getVertices() {
        return vertices.keySet();
    }

    public Set<UndirectedEdge> getEdges() {
        return edges;
    }

    public Set<UndirectedEdge> getAdjacentEdges(Vertex v) {
        return vertices.get(v);
    }

    @Override
    public String toString() {
        return "Vertices: " + vertices.keySet().toString() + "\n" + "Edges: " + edges.toString();
    }
}
