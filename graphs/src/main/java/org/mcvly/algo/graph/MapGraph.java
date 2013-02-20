package org.mcvly.algo.graph;

import java.util.*;

/**
 * User: RMalyona
 * Date: 19.02.13
 */
public class MapGraph {
    private Map<Vertex, List<Vertex>> graph;

    public MapGraph(Map<Vertex, List<Vertex>> graph) {
        this.graph = graph;
    }

    public MapGraph(MapGraph data) {
        this.graph = new HashMap<Vertex, List<Vertex>>(data.size());
        for (Map.Entry<Vertex, List<Vertex>> entry: data.entrySet()) {
            graph.put(new Vertex(entry.getKey().getId()), new LinkedList<Vertex>(entry.getValue()));
        }
    }

    public List<Vertex> getAdjacentVertices(Vertex v) {
        return graph.get(v);
    }

    public int size() {
        return graph.size();
    }

    public Set<Map.Entry<Vertex, List<Vertex>>> entrySet() {
        return graph.entrySet();
    }

    public Set<Vertex> keySet() {
        return graph.keySet();
    }

    public Set<Vertex> getVertices() {
        return graph.keySet();
    }

    public void removeVertex(Vertex vertex) {
        graph.remove(vertex);
    }

    public MapGraph getReversedGraph() {
        Map<Vertex, List<Vertex>> reversedGraph = new HashMap<Vertex, List<Vertex>>(graph.size());

        for (Map.Entry<Vertex,List<Vertex>> entry : graph.entrySet()) {
            if (!reversedGraph.containsKey(entry.getKey())) {
                reversedGraph.put(entry.getKey(), new LinkedList<Vertex>());
            }

            for (Vertex v : entry.getValue()) {
                if (!reversedGraph.containsKey(v)) {
                    reversedGraph.put(v, new LinkedList<Vertex>());
                }
                reversedGraph.get(v).add(entry.getKey());
            }
        }

        return new MapGraph(reversedGraph);
    }

    public Vertex getVertex(Vertex s) {
        for (Vertex v : graph.keySet()) {
            if (v.equals(s)) {
                return v;
            }
        }
        return null;
    }
}
