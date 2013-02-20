package org.mcvly.algo.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class Vertex {
    private String id;
    Map<String, Object> attributes;

    public Vertex(String id) {
        this.id = id;
        attributes = new HashMap<String, Object>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAttribute(String attribute, Object value) {
        attributes.put(attribute, value);
    }

    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

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
