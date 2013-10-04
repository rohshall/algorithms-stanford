package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author: mcvly
 * @since: 10/4/13
 */
public class MatrixGraph implements DirectedGraph {

    private int n;
    private int[][] adjacencyMatrix;

    public MatrixGraph(int n) {
        this.n = n;
        adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    adjacencyMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public int getVertexCount() {
        return n;
    }

    public int getEdgeCost(int v1, int v2) {
        return adjacencyMatrix[v1][v2];
    }

    public void addEdge(int v1, int v2, int cost) {
        adjacencyMatrix[v1-1][v2-1] = cost;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
