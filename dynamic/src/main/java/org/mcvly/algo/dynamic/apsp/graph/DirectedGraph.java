package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author: mcvly
 * @since: 10/4/13
 */
public interface DirectedGraph {

    int getVertexCount();

    double getEdgeCost(int v1, int v2);

    void addEdge(int v1, int v2, double cost);

}
