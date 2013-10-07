package org.mcvly.algo.dynamic.apsp.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: mcvly
 * @since: 10/4/13
 */
public class MatrixGraph<T> implements DirectedGraph<T> {

    private int n;
    private double[][] adjacencyMatrix;
    private Map<Vertex<T>, Integer> mapping;
    private Map<Integer, Vertex<T>> reverseMapping;
    private int currentN;

    public MatrixGraph(int n) {
        this.n = n;
        adjacencyMatrix = new double[n][n];
        mapping = new HashMap<>((int) (n / 0.75));
        reverseMapping = new HashMap<>((int) (n / 0.75));
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

    public double getEdgeCost(Vertex<T> v1, Vertex<T> v2) {
        if (!mapping.containsKey(v1) || !mapping.containsKey(v2)) {
            throw new RuntimeException("no such vertex");
        }
        return adjacencyMatrix[mapping.get(v1)][mapping.get(v2)];
    }

    public void addEdge(Vertex<T> v1, Vertex<T> v2, double cost) {
        int n1 = mapping.containsKey(v1) ? mapping.get(v1) : -1;
        int n2 = mapping.containsKey(v2) ? mapping.get(v2) : -1;

        if ((n1 == -1 && n2 == -1 && currentN > n -2) || ((n1 == -1 || n2 == -1) && currentN > n-1)) {
            throw new RuntimeException("Cannot add more edges. Capacity is full");
        }

        if (n1 == -1) {
            n1 = currentN;
            mapping.put(v1, currentN);
            reverseMapping.put(currentN, v1);
            currentN++;
        }
        if (n2 == -1) {
            n2 = currentN;
            reverseMapping.put(currentN, v2);
            mapping.put(v2, currentN);
            currentN++;
        }

        adjacencyMatrix[n1][n2] = cost;
    }

    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Map<Vertex<T>, Integer> getMapping() {
        return mapping;
    }

    public Map<Integer, Vertex<T>> getReverseMapping() {
        return reverseMapping;
    }
}
