package org.mcvly.algo.graph.dijsktra;

/**
 * User: RMalyona
 * Date: 26.02.13
 */
public class Edge {
    private Vertex target;
    private double weight;

    public Edge(Vertex target, double weight) {
        this.target = target;
        this.weight = weight;
    }

    public Vertex getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }
}
