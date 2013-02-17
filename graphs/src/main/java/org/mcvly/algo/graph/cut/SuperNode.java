package org.mcvly.algo.graph.cut;

import org.mcvly.algo.graph.Vertex;

import java.util.LinkedList;
import java.util.List;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class SuperNode {

    private Vertex originalVertex;
    private List<Vertex> absorbedVertices;

    public SuperNode(Vertex vertex) {
        this.originalVertex = vertex;
        absorbedVertices = new LinkedList<Vertex>();
    }

    public void absorbVertex(SuperNode v) {

        absorbedVertices.add(v.getOriginalVertex());
        for (Vertex o : v.getAbsorbedVertices()) {
            absorbedVertices.add(o);
        }
    }

    public Vertex getOriginalVertex() {
        return originalVertex;
    }

    public List<Vertex> getAbsorbedVertices() {
        return absorbedVertices;
    }

    public boolean isVertexSame(SuperNode node) {
        return originalVertex.getId().equals(node.getOriginalVertex().getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperNode superNode = (SuperNode) o;

        if (originalVertex != null ? !originalVertex.equals(superNode.originalVertex) : superNode.originalVertex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = originalVertex != null ? originalVertex.hashCode() : 0;
        return result;
    }

    @Override
    public String toString() {
//        StringBuilder b = new StringBuilder();
//        b.append(originalVertex);
//        if (absorbedVertices.size() > 0) {
//            b.append(" [");
//            for (Vertex node : getAbsorbedVertices() ) {
//                b.append(node);
//                b.append(" ");
//            }
//            b.append("]");
//        }
//        return b.toString();
        return originalVertex.toString();
    }
}
