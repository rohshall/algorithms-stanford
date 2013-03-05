package org.mcvly.algo.graph.dijsktra;

import java.util.ArrayList;
import java.util.List;

/**
 * User: RMalyona
 * Date: 26.02.13
 */
public class Graph {
    private List<Vertex> vertices;

    public Graph() {
        this.vertices = new ArrayList<Vertex>();
    }

    public Graph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public Vertex getVertexByName(String name) {
        for (Vertex v : vertices) {
            if (name.equals(v.getName())) {
                return v;
            }
        }
        return null;
    }

    public List<Vertex>  getVertices() {
        return vertices;
    }

    public void addVertex(Vertex v) {
        vertices.add(v);
    }

    public int size() {
        return vertices.size();
    }
}
