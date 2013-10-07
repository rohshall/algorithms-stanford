package org.mcvly.algo.dynamic.apsp.graph;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 07.10.13
 */
public class Vertex<T> {

    public Vertex(T label) {
        this.label = label;
    }

    private T label;

    public T getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vertex vertex = (Vertex) o;

        if (label != null ? !label.equals(vertex.label) : vertex.label != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return label != null ? label.hashCode() : 0;
    }

    @Override
    public String toString() {

        return label.toString();

    }
}
