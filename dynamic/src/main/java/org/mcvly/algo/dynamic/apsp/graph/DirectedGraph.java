package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author: mcvly
 * @since: 10/4/13
 */
public interface DirectedGraph<T> {

    int getVertexCount();

    double getEdgeCost(Vertex<T> v1, Vertex<T> v2);

    void addEdge(Vertex<T> v1, Vertex<T> v2, double cost);

}
