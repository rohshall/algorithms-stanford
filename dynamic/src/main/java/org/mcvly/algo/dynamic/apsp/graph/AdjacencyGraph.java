package org.mcvly.algo.dynamic.apsp.graph;

import java.util.*;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class AdjacencyGraph implements DirectedGraph {

    private int vertexCount;
    private int edgesCount;
    private Map<Integer, List<Edge>> graph;

    public AdjacencyGraph(int vertexCount, int edgesCount) {
        this.vertexCount = vertexCount;
        this.edgesCount = edgesCount;
        this.graph = new HashMap<>((int) (vertexCount / 0.75));
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Collection<Integer> getVertices() {
        return graph.keySet();
    }

    public int getEdgesCount() {
        return edgesCount;
    }

    public double getEdgeCost(int v1, int v2) {
        if (v1 == v2) {
            return 0;
        }

        if (graph.containsKey(v1)) {
            List<Edge> v1Adjacent = graph.get(v1);
            for (Edge e : v1Adjacent) {
                if (v2 == e.getTo()) {
                    return e.getCost();
                }
            }
        }

        return Double.POSITIVE_INFINITY;
    }

    public void addEdge(int v1, int v2, double cost) {
        if (!graph.containsKey(v1)) {
            graph.put(v1, new ArrayList<Edge>());

        }
        graph.get(v1).add(new Edge(v1, v2, cost));
    }

    public Map<Integer, List<Edge>> getGraph() {
        return graph;
    }

    public List<Edge> adj(int v) {
        if (!graph.containsKey(v)) {
            return new ArrayList<>();
        }

        return graph.get(v);
    }

}
