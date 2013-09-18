package org.mcvly.algo.greedy.mst.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mcvly.algo.greedy.mst.core.UndirectedEdge;
import org.mcvly.algo.greedy.mst.core.UndirectedGraph;
import org.mcvly.algo.greedy.mst.core.Vertex;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 18.09.13
 */
public class UnionFindImplementation extends AbstractKruskalAlgorithm {

    Map<Vertex, Union> union = new HashMap<>();

    private class Union {
        private List<Vertex> vertices;
        private String name;

        public Union(Vertex vertex) {
            this.name = vertex.getName();
            this.vertices = new ArrayList<>(Arrays.asList(vertex));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Union union = (Union) o;

            if (!name.equals(union.name)) return false;
            if (!vertices.equals(union.vertices)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = vertices.hashCode();
            result = 31 * result + name.hashCode();
            return result;
        }
    }

    @Override
    public UndirectedGraph minimumSpanningTree(UndirectedGraph g) {
        for (Vertex v : g.getVertices()) {
            union.put(v, new Union(v));
        }

        return super.minimumSpanningTree(g);
    }

    @Override
    protected boolean doesCreateCycle(UndirectedEdge edge, UndirectedGraph graph) {
        return (union.get(edge.getFirstVertex()).name).equals(union.get(edge.getSecondVertex()).name);
    }

    /**
     * Need to maintain Union-Find invariant here
     */
    @Override
    protected void addEdgeToGraph(UndirectedGraph graph, UndirectedEdge edge) {
        super.addEdgeToGraph(graph, edge);

        // fuse unions
        Union larger, smaller;

        if (union.get(edge.getFirstVertex()).vertices.size() > union.get(edge.getSecondVertex()).vertices.size()) {
            larger = union.get(edge.getFirstVertex());
            smaller = union.get(edge.getSecondVertex());
        } else {
            larger = union.get(edge.getSecondVertex());
            smaller = union.get(edge.getFirstVertex());
        }

        larger.vertices.addAll(smaller.vertices);
        for (Vertex vertex : smaller.vertices) {
            union.put(vertex, larger);
        }
    }
}
