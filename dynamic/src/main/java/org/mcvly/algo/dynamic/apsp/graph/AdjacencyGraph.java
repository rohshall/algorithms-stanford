package org.mcvly.algo.dynamic.apsp.graph;

import java.util.*;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class AdjacencyGraph<T> implements DirectedGraph<T> {

    private int vertexCount;
    private Map<Vertex<T>, List<Edge<T>>> graph;

    public AdjacencyGraph() {
        this.graph = new HashMap<>();
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Collection<Vertex<T>> getVertices() {
        return graph.keySet();
    }

    public double getEdgeCost(Vertex<T> v1, Vertex<T> v2) {
        if (v1 == v2) {
            return 0;
        }

        if (graph.containsKey(v1)) {
            List<Edge<T>> v1Adjacent = graph.get(v1);
            for (Edge<T> e : v1Adjacent) {
                if (v2 == e.getTo()) {
                    return e.getCost();
                }
            }
        }

        return Double.POSITIVE_INFINITY;
    }

    public void addEdge(Vertex<T> v1, Vertex<T> v2, double cost) {
        if (!graph.containsKey(v1)) {
            graph.put(v1, new ArrayList<Edge<T>>());
            vertexCount += 1;
        }
        graph.get(v1).add(new Edge<T>(v1, v2, cost));
    }

    public void removeVertex(Vertex<T> v) {
        graph.remove(v);
        vertexCount -= 1;
    }

    public Map<Vertex<T>, List<Edge<T>>> getGraph() {
        return graph;
    }

    public List<Edge<T>> adj(Vertex<T> v) {
        if (!graph.containsKey(v)) {
            return new ArrayList<>();
        }

        return graph.get(v);
    }

}
