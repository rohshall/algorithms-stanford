package org.mcvly.algo.graph;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class Edge<T> {
    private String id;
    private T a;
    private T b;

    public Edge(String id) {
        this.id = id;
    }

    public Edge(T a, T b, String id) {
        this.a = a;
        this.b = b;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getA() {
        return a;
    }

    public void setA(T a) {
        this.a = a;
    }

    public T getB() {
        return b;
    }

    public void setB(T b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        // edge.a == edge.b && edge.b == edge.a are equal
        if (!a.equals(edge.a) && !a.equals(edge.b)) return false;
        if (!b.equals(edge.b) && !b.equals(edge.a)) return false;
        if (!id.equals(edge.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + a.hashCode() + + b.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + a + "," + b + "]";
    }
}
