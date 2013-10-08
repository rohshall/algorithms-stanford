package org.mcvly.algo.dynamic.apsp;

import org.mcvly.algo.dynamic.apsp.graph.*;
import org.mcvly.algo.dynamic.sssp.BellmanFordAlgorithm;
import org.mcvly.algo.dynamic.sssp.DijkstraAlgorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 07.10.13
 */
public class Johnson<T> {

    private AdjacencyGraph<T> graph;
    private Map<Vertex<T>, Double> reweighted;
    private Map<Vertex<T>, Map<Vertex<T>, Double>> distances;
//    private int[][] paths;
    private Vertex<T> nullVertex = new Vertex<T>(null);
    private int n;

    public Johnson(AdjacencyGraph<T> graph) {
        this.graph = graph;
        this.n = graph.getVertexCount() + 1;
        reweighted = new HashMap<>((int) (n / 0.75));
        distances = new HashMap<>();
//        paths = new int[n][n];
    }

    public void runAlgorithm() throws NegativeCycleException {
        // 1. add surrogate vertex '-1' with edges distance 0 to all other vertices
        makeSurrogateGraph();
        // 2. compute shortest path distances to all vertices from '-1'
        BellmanFordAlgorithm<T> bellmanFordAlgorithm = new BellmanFordAlgorithm<T>(graph, nullVertex);
        bellmanFordAlgorithm.runAlgorithm();
        // 3. fill reweighting table by shortest path distances to each vertex from '-1'
        graph.removeVertex(nullVertex);
        fillReweightedTable(bellmanFordAlgorithm);
        // 4. run Dijkstra algorithm for each vertex as source with reweighted edges
        DijkstraAlgorithm<T> dijkstraAlgorithm = new DijkstraAlgorithm<>(graph);
        int i = 0;
        for (Vertex<T> u : graph.getVertices()) {
            long start = System.currentTimeMillis();
            dijkstraAlgorithm.runAlgorithm(u);
//            distances.put(u, new HashMap<Vertex<T>, Double>());
            for (Vertex<T> v : graph.getVertices()) {
                // 5. reconstruct shortest path distances from results of Dijkstra algorithm
//                distances.get(u).put(v, dijkstraAlgorithm.getVertexDistance(v) - reweighted.get(u) + reweighted.get(v));
            }
        }
    }

    public double shortestPathLength(Vertex<T> v1, Vertex<T> v2) {
        return distances.get(v1).get(v2);
    }

    public double minOfShortest() {
        double minVal = Double.POSITIVE_INFINITY;
        for (Vertex<T> u : graph.getVertices()) {
            for (Vertex<T> v : graph.getVertices()) {
                if (!v.equals(u)) {
                    double dist = distances.get(u).get(v);
                    if (dist < minVal) {
                        minVal = dist;
                    }
                }
            }
        }

        return minVal;
    }

    private AdjacencyGraph<T> makeSurrogateGraph() {
        graph.addEdge(nullVertex, nullVertex, 0);
        for (Vertex<T> v : graph.getVertices()) {
            graph.addEdge(nullVertex, v, 0);
        }

        return graph;
    }

    private void fillReweightedTable(BellmanFordAlgorithm<T> bellmanFordAlgorithm) {
        for (Vertex<T> u : graph.getVertices()) {
            reweighted.put(u, bellmanFordAlgorithm.getShortestPathLength(u));
        }
        for (Vertex<T> u : graph.getVertices()) {
            for (Edge<T> e : graph.adj(u)) {
                e.setCost(e.getCost() + reweighted.get(e.getFrom()) - reweighted.get(e.getTo()));
            }
        }

    }

    public static void main(String[] args) throws IOException, NegativeCycleException {
        String fileName = "graphs/large.txt";
        //String fileName = "graphs/g3.txt"; // -19
        AdjacencyGraph<Integer> graph = (AdjacencyGraph<Integer>) DirectedGraphReader.readIntGraph(fileName, GraphFactory.Graphs.ADJACENCY);
        Johnson<Integer> algorithm = new Johnson<>(graph);
        algorithm.runAlgorithm();
        System.out.println(algorithm.minOfShortest());
    }
}
