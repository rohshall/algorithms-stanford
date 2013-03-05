package org.mcvly.algo.graph.dijsktra;

/**
 * User: RMalyona
 * Date: 26.02.13
 */
public class Vertex {
    private Edge[] adjacents;
    private Vertex previous;
    private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public Vertex(String name, Edge[] adjacents) {
        this.name = name;
        this.adjacents = adjacents;
    }

    public Edge[] getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(Edge[] e) {
        this.adjacents = e;
    }

    public String getName() {
        return name;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex v) {
        this.previous = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        if (name != null ? !name.equals(vertex.name) : vertex.name != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
