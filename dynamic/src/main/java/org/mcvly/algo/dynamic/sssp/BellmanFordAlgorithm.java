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
public class BellmanFordAlgorithm<T> {

    private Map<Vertex<T>, Double> dist;
    private Map<Vertex<T>, Vertex<T>> pred;
    private AdjacencyGraph<T> graph;
    private Vertex<T> start;
    private int n;

    public BellmanFordAlgorithm(AdjacencyGraph<T> graph, Vertex<T> s) {
        this.graph = graph;
        n = graph.getVertexCount();
        this.start = s;
        dist = new HashMap<>();
        pred = new HashMap<>();
        init();
    }

    private void init() {
        for (Vertex<T> v : graph.getVertices()) {
            dist.put(v, Double.POSITIVE_INFINITY);
        }
        dist.put(start, 0d);
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
        for (Vertex<T> v : graph.getVertices()) {
            for (Edge<T> e : graph.adj(v)) {
                Vertex<T> w = e.getTo();
                if (dist.get(w) > dist.get(v) + e.getCost()) { // relax v-w
                    dist.put(w, dist.get(v) + e.getCost());
                    pred.put(w, v);
                    changed = true;
                }
            }
        }
        return changed;
    }

    public double getShortestPathLength(Vertex<T> t) {
        return dist.get(t);
    }

    public List<Vertex<T>> getShortestPath(Vertex<T> t) {
        List<Vertex<T>> res = new ArrayList<>();
        Vertex<T> w = pred.get(t);
        if (!w.equals(start)) {
            res.add(w);
            res.addAll(getShortestPath(w));
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
        String fileName = "graphs/g3.txt";
        //String fileName = "graphs/g3.txt"; // -19 : 399-904
        AdjacencyGraph<Integer> graph = (AdjacencyGraph<Integer>) DirectedGraphReader.readIntGraph(fileName, GraphFactory.Graphs.ADJACENCY);
        BellmanFordAlgorithm<Integer> algorithm = new BellmanFordAlgorithm<>(graph, new Vertex<>(399));
        algorithm.runAlgorithm();

        System.out.println(algorithm.getShortestPathLength(new Vertex<>(904)));
        System.out.println(algorithm.getShortestPath(new Vertex<>(904)));
    }
}
