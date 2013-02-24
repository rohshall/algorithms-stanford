package org.mcvly.algo.graph;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class Edge {
    private Object a;
    private Object b;

    public Edge(Object a, Object b) {
        this.a = a;
        this.b = b;
    }

    public Object getA() {
        return a;
    }

    public void setA(Object a) {
        this.a = a;
    }

    public Object getB() {
        return b;
    }

    public void setB(Object b) {
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + a.hashCode() + + b.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + a + "," + b + "]";
    }
}
