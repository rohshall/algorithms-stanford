package org.mcvly.algo.dynamic.apsp;

import org.mcvly.algo.dynamic.apsp.graph.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class FloydWarshall {

    private MatrixGraph graph;
    private double[][] array;
    private int[][] paths;
    int n;

    public FloydWarshall(MatrixGraph graph) {
        this.graph = graph;
        n = graph.getVertexCount();
        array = graph.getAdjacencyMatrix();
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
                    if (array[i][k] != Double.POSITIVE_INFINITY && array[k][j] != Double.POSITIVE_INFINITY) {
                        if (array[i][j] > array[i][k] + array[k][j]) {
                            array[i][j] = array[i][k] + array[k][j];
                            paths[i][j] = k;
                        }
                    }
                }
            }
        }
    }

    private boolean hasNegativeCycle() {
        for (int i = 0; i < n; i++) {
            if (array[i][i] < 0) {
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
                    if (array[i][j] < minVal) {
                        minVal = array[i][j];
                    }
                }
            }
        }

        return minVal;
    }

    public double shortestPathLength(int v1, int v2) {
        return array[v1][v2];
    }

    public List<Integer> getShortestPath(int v1, int v2) {
        List<Integer> res = new ArrayList<>();
        int k = paths[v1][v2];
        if (k != -1) {
            res.add(k);
            res.addAll(getShortestPath(v1, k));
            res.addAll(getShortestPath(k, v2));
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
        MatrixGraph graph = (MatrixGraph) DirectedGraphReader.readGraph(fileName, GraphFactory.Graphs.MATRIX);
        FloydWarshall algorithm = new FloydWarshall(graph);
        algorithm.runAlgorithm();
        System.out.println(algorithm.minOfShortest());
        System.out.println(algorithm.getShortestPath(399, 903));
    }
}
