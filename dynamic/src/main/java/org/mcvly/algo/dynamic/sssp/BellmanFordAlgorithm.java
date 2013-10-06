package org.mcvly.algo.dynamic.sssp;

import org.mcvly.algo.dynamic.apsp.graph.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: mcvly
 * @since: 10/5/13
 */
public class BellmanFordAlgorithm {

    Map<Integer, Integer> mapping;
    Map<Integer, Integer> mappingReversed;
    private double[] dist;
    private int[] pred;
    private AdjacencyGraph graph;
    int n;
    int start;

    public BellmanFordAlgorithm(AdjacencyGraph graph, int s) {
        this.graph = graph;
        n = graph.getVertexCount();
        mapping = new HashMap<>();
        mappingReversed = new HashMap<>();
        this.start = s;
        dist = new double[n];
        pred = new int[n];
        init();
    }

    private void init() {
        int i = 0;
        for (int n : graph.getVertices()) {
            dist[i] = Double.POSITIVE_INFINITY;
            mapping.put(n, i);
            mappingReversed.put(i, n);
            i++;
        }
        start = mapping.get(start);
        dist[start] = 0;
    }

    public void runAlgorithm() throws NegativeCycleException {
        for (int i = 1; i < n; i++) {
            boolean changed = iterationChanged();
            if (!changed) {
                return;
            }
        }
        boolean isNegativeCycle = iterationChanged();
        if (isNegativeCycle) {
            throw new NegativeCycleException();
        }
    }

    private boolean iterationChanged() {
        boolean changed = false;
        for (int u : graph.getVertices()) {
            int v = mapping.get(u);
            for (Edge e : graph.adj(u)) {
                int w = mapping.get(e.getTo());
                if (dist[v] != Double.POSITIVE_INFINITY && dist[w] > dist[v] + e.getCost()) { // relax v-w
                    dist[w] = dist[v] + e.getCost();
                    pred[w] = v;
                    changed = true;
                }
            }
        }
        return changed;
    }

    public double getShortestPathLength(int t) {
        return dist[mapping.get(t)];
    }

    public List<Integer> getShortestPath(int t) {
        List<Integer> res = new ArrayList<>();
        int w = pred[mapping.get(t)];
        if (w != start) {
            res.add(mappingReversed.get(w));
            res.addAll(getShortestPath(mappingReversed.get(w)));
        }

        return res;
    }


    public static void main(String[] args) throws IOException, NegativeCycleException {
        /**
         * Shortest paths for each vertex (right side of colon is parent node for vertex in left side of colon):
         1 - {2: 3, 3: 4, 4: 5, 5: 1}
         2 - {1: 4, 3: 4, 4: 2, 5: 1}
         3 - {1: 4, 2: 3, 4: 2, 5: 1}
         4 - {1: 4, 2: 3, 3: 4, 5: 1}
         5 - {1: 4, 2: 3, 3: 4, 4: 5}
         Shortest paths weights for each vertex (right side of colon is weight for vertex in left side of colon):
         1 - {1: 0, 2: 1, 3: -3, 4: 2, 5: -4}
         2 - {1: 3, 2: 0, 3: -4, 4: 1, 5: -1}
         3 - {1: 7, 2: 4, 3: 0, 4: 5, 5: 3}
         4 - {1: 2, 2: -1, 3: -5, 4: 0, 5: -2}
         5 - {1: 8, 2: 5, 3: 1, 4: 6, 5: 0}
         Shortest shortest path is -5.
         */
        String fileName = "graphs/mediumEWD.txt";
        //String fileName = "graphs/g3.txt"; // -19
        AdjacencyGraph graph = (AdjacencyGraph) DirectedGraphReader.readGraph(fileName, GraphFactory.Graphs.ADJACENCY);
        BellmanFordAlgorithm algorithm = new BellmanFordAlgorithm(graph, 0);
        algorithm.runAlgorithm();

        System.out.println(algorithm.getShortestPathLength(4));
       System.out.println(algorithm.getShortestPath(4   ));
    }
}
