package org.mcvly.algo.dynamic.apsp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.mcvly.algo.dynamic.apsp.graph.AdjacencyGraph;
import org.mcvly.algo.dynamic.apsp.graph.DirectedGraphReader;
import org.mcvly.algo.dynamic.apsp.graph.Edge;
import org.mcvly.algo.dynamic.apsp.graph.GraphFactory;
import org.mcvly.algo.dynamic.apsp.graph.NegativeCycleException;
import org.mcvly.algo.dynamic.apsp.graph.Vertex;
import org.mcvly.algo.dynamic.sssp.BellmanFordAlgorithm;
import org.mcvly.algo.dynamic.sssp.DijkstraAlgorithm;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 07.10.13
 */
public class Johnson<T> {

    private AdjacencyGraph<T> graph;
    private Map<Vertex<T>, MapItem> mapping;
    private double[] distances;
    private double[] shortestDistances;
    private Vertex<T> nullVertex = new Vertex<T>(null);
    private int n;

    private class MapItem {
        private int index;
        private double reweightedValue;

        private MapItem(int index) {
            this.index = index;
        }

        private int getIndex() {
            return index;
        }

        private void setIndex(int index) {
            this.index = index;
        }

        private double getReweightedValue() {
            return reweightedValue;
        }

        private void setReweightedValue(double reweightedValue) {
            this.reweightedValue = reweightedValue;
        }
    }

    public Johnson(AdjacencyGraph<T> graph) {
        this.graph = graph;
        this.n = graph.getVertexCount() + 1;
        int i = 0;
        mapping = new HashMap<>((int) (n / 0.75));
        for (Vertex<T> v : graph.getVertices()) {
            mapping.put(v, new MapItem(i));
            i++;
        }
        distances = new double[n];
        shortestDistances = new double[n];
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
            int uInd = mapping.get(u).getIndex();

            for (Vertex<T> v : graph.getVertices()) {
                // 5. reconstruct shortest path distances from results of Dijkstra algorithm
                int vInd = mapping.get(v).getIndex();
                double uWeight = mapping.get(u).getReweightedValue();
                double vWeight = mapping.get(v).getReweightedValue();
                distances[vInd] = dijkstraAlgorithm.getVertexDistance(v) - uWeight + vWeight;
            }
            shortestDistances[uInd] = minForCurrent(uInd);
        }
    }

    public double minForCurrent(int uInd) {
        double minVal = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            if (i != uInd) {
                double dist = distances[i];
                if (dist < minVal) {
                    minVal = dist;
                }
            }
        }

        return minVal;
    }

    public double minOfMin() {
        double minVal = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {

            double dist = shortestDistances[i];
            if (dist < minVal) {
                minVal = dist;
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
            mapping.get(u).setReweightedValue(bellmanFordAlgorithm.getShortestPathLength(u));
        }
        for (Vertex<T> u : graph.getVertices()) {
            for (Edge<T> e : graph.adj(u)) {
                double fromWeight = mapping.get(e.getFrom()).getReweightedValue();
                double toWeight = mapping.get(e.getTo()).getReweightedValue();
                e.setCost(e.getCost() + fromWeight - toWeight);
            }
        }

    }

    public static void main(String[] args) throws IOException, NegativeCycleException {
        String fileName = "graphs/large.txt";
        //String fileName = "graphs/g3.txt"; // -19
        AdjacencyGraph<Integer> graph = (AdjacencyGraph<Integer>) DirectedGraphReader.readIntGraph(fileName, GraphFactory.Graphs.ADJACENCY);
        Johnson<Integer> algorithm = new Johnson<>(graph);
        algorithm.runAlgorithm();
        System.out.println(algorithm.minOfMin());
    }
}
