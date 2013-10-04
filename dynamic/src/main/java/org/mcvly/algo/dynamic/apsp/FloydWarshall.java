package org.mcvly.algo.dynamic.apsp;

import java.io.IOException;

import org.mcvly.algo.dynamic.apsp.graph.DirectedGraph;
import org.mcvly.algo.dynamic.apsp.graph.DirectedGraphReader;
import org.mcvly.algo.dynamic.apsp.graph.NegativeCycleException;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class FloydWarshall {

    private DirectedGraph graph;
    private int[][] array;
    int n;

    public FloydWarshall(DirectedGraph graph) {
        this.graph = graph;
        n = graph.getVertexCount();
        array = new int[n+1][n+1];
    }

    private void preprocess() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                array[i][j] = graph.edgeCost(i, j);
            }
        }
    }

    private void mainLoop() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (array[i][k] != Integer.MAX_VALUE && array[k][j] != Integer.MAX_VALUE) {
                        array[i][j] = Math.min(array[i][j], array[i][k] + array[k][j]);
                    }
                }
            }
        }
    }

    private boolean hasNegativeCycle() {
        for (int i = 0; i <= n; i++) {
            if (array[i][i] < 0) {
                return true;
            }
        }
        return false;
    }

    public void runAlgorithm() throws NegativeCycleException {
        preprocess();
        mainLoop();

        if (hasNegativeCycle()) {
            throw new NegativeCycleException();
        }
    }

    public int minOfShortest() {
        int minVal = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    if (array[i][j] < minVal) {
                        minVal = array[i][j];
                    }
                }
            }
        }

        return minVal;
    }

    public int shortestPath(int v1, int v2) {
        return array[v1][v2];
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
        String fileName = "graphs/large.txt";
        //String fileName = "graphs/g3.txt"; // -19
        DirectedGraph graph = DirectedGraphReader.readGraph(fileName);
        FloydWarshall algorithm = new FloydWarshall(graph);
        algorithm.runAlgorithm();
        System.out.println(algorithm.minOfShortest());
    }
}
