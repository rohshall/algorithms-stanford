package org.mcvly.algo.graph;

import java.util.*;

/**
 * User: RMalyona
 * Date: 19.02.13
 */
public class MapGraph {
    private Map<Vertex, Vertex[]> graph;

    public MapGraph(Map<Vertex, Vertex[]> graph) {
        this.graph = graph;
    }

    public MapGraph(MapGraph data) {
        this.graph = new HashMap<Vertex, Vertex[]>(data.size());
        for (Map.Entry<Vertex, Vertex[]> entry: data.entrySet()) {
            graph.put(new Vertex(entry.getKey().getId()), entry.getValue());
        }
    }

    public Vertex[] getAdjacentVertices(Vertex v) {
        return graph.get(v);
    }

    public void setAdjacentVertices(Vertex v, Vertex[] array) {
        graph.put(v, array);
    }

    public int size() {
        return graph.size();
    }

    public Set<Map.Entry<Vertex, Vertex[]>> entrySet() {
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
        Map<Vertex, Vertex[]> reversedGraph = new HashMap<Vertex, Vertex[]>();

        for (Map.Entry<Vertex,Vertex[]> entry : graph.entrySet()) {
            if (!reversedGraph.containsKey(entry.getKey())) {
                reversedGraph.put(entry.getKey(), null);
            }

            if (entry.getValue() != null) {
                for (Vertex v : entry.getValue()) {
                    reversedGraph.put(v, concat(reversedGraph.get(v),entry.getKey()));
                }
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

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = null;
        if (first != null && second != null) {
            result = Arrays.copyOf(first, first.length + second.length);
            System.arraycopy(second, 0, result, first.length, second.length);
            first = null;
            second = null;
        } else if (first == null && second != null) {
            result = second;
        } else if (first != null) {
            result = first;
        }
        return result;
    }

    public static Vertex[] concat(Vertex[] org, Vertex added) {
        if (org == null || org.length == 0) {
            return new Vertex[] {added};
        }
        Vertex[] result = Arrays.copyOf(org, org.length +1);
        result[org.length] = added;
        org = null;
        return result;
    }
}
