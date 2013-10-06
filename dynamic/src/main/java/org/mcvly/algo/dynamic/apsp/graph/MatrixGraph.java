package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author: mcvly
 * @since: 10/4/13
 */
public class MatrixGraph implements DirectedGraph {

    private int n;
    private double[][] adjacencyMatrix;

    public MatrixGraph(int n) {
        this.n = n;
        adjacencyMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    public int getVertexCount() {
        return n;
    }

    public double getEdgeCost(int v1, int v2) {
        return adjacencyMatrix[v1][v2];
    }

    public void addEdge(int v1, int v2, double cost) {
        adjacencyMatrix[v1-1][v2-1] = cost;
    }

    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
