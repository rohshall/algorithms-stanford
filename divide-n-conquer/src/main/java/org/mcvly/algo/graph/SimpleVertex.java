package org.mcvly.algo.graph;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class SimpleVertex {
    private String id;

    public SimpleVertex(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleVertex vertex = (SimpleVertex) o;

        if (!id.equals(vertex.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
