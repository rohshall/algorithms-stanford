package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class Edge<T> {

    private Vertex<T> from, to;
    private double cost;

    public Edge(Vertex<T> from, Vertex<T> to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public Vertex<T> getFrom() {
        return from;
    }

    public void setFrom(Vertex<T> from) {
        this.from = from;
    }

    public Vertex<T> getTo() {
        return to;
    }

    public void setTo(Vertex<T> to) {
        this.to = to;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Edge edge = (Edge) o;

        if (Double.compare(edge.cost, cost) != 0) {
            return false;
        }
        if (from != null ? !from.equals(edge.from) : edge.from != null) {
            return false;
        }
        if (to != null ? !to.equals(edge.to) : edge.to != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
