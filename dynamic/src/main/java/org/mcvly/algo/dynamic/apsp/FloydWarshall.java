package org.mcvly.algo.dynamic.apsp;

import org.mcvly.algo.dynamic.apsp.graph.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class FloydWarshall<T> {

    private MatrixGraph<T> graph;
    private double[][] dist;
    private int[][] paths;
    int n;

    public FloydWarshall(MatrixGraph<T> graph) {
        this.graph = graph;
        n = graph.getVertexCount();
        dist = graph.getAdjacencyMatrix();
        paths = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                paths[i][j] = -1;
            }
        }
    }

    private void mainLoop() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Double.POSITIVE_INFINITY && dist[k][j] != Double.POSITIVE_INFINITY) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                            paths[i][j] = k;
                        }
                    }
                }
            }
        }
    }

    private boolean hasNegativeCycle() {
        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) {
                return true;
            }
        }
        return false;
    }

    public void runAlgorithm() throws NegativeCycleException {
        mainLoop();

        if (hasNegativeCycle()) {
            throw new NegativeCycleException();
        }
    }

    public double minOfShortest() {
        double minVal = Double.POSITIVE_INFINITY;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (dist[i][j] < minVal) {
                        minVal = dist[i][j];
                    }
                }
            }
        }

        return minVal;
    }

    public double shortestPathLength(Vertex<T> v1, Vertex<T> v2) {
        return dist[graph.getMapping().get(v1)][graph.getMapping().get(v2)];
    }

    public List<Vertex<T>> getShortestPath(Vertex<T> v1, Vertex<T> v2) {
        List<Vertex<T>> res = new ArrayList<>();
        int k = paths[graph.getMapping().get(v1)][graph.getMapping().get(v2)];
        if (k != -1) {
            Vertex<T> intermediate = graph.getReverseMapping().get(k);
            res.add(intermediate);
            res.addAll(getShortestPath(v1, intermediate));
            res.addAll(getShortestPath(intermediate, v2));
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
        //String fileName = "graphs/g3.txt"; // -19
        MatrixGraph<Integer> graph = (MatrixGraph<Integer>) DirectedGraphReader.readIntGraph(fileName, GraphFactory.Graphs.MATRIX);
        FloydWarshall<Integer> algorithm = new FloydWarshall<>(graph);
        algorithm.runAlgorithm();
        System.out.println(algorithm.minOfShortest());
        System.out.println(algorithm.getShortestPath(new Vertex<>(399), new Vertex<>(904)));
    }
}
