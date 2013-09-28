package org.mcvly.algo.greedy.mst.kruskal;

import org.mcvly.algo.greedy.mst.core.UndirectedEdge;
import org.mcvly.algo.greedy.mst.core.UndirectedGraph;
import org.mcvly.algo.greedy.mst.core.Vertex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author: mcvly
 * @since: 9/18/13
 */
public class StraightforwardImplementation extends AbstractKruskalAlgorithm {

    @Override
    protected boolean doesCreateCycle(UndirectedEdge candidate, UndirectedGraph graph) {
        Vertex source = candidate.getFirstVertex();
        Vertex dest = candidate.getSecondVertex();

        if (!graph.getVertices().contains(source) || !graph.getVertices().contains(dest)) {
            return false;
        }
        // if there's already source-dest path, then candidate edge creates cycle
        Set<Vertex> visited = bfs(graph, source);

        return visited.contains(dest);
    }

    private Set<Vertex> bfs(UndirectedGraph g, Vertex start) {
        Set<Vertex> visited = new HashSet<>(g.getVertices().size());

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(start);

        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex v = queue.remove();

            Set<UndirectedEdge> adjacentEdges = g.getAdjacentEdges(v);
            for (UndirectedEdge edge : adjacentEdges) {
                Vertex v1 = null;
                if (!edge.getFirstVertex().equals(v)) {
                    v1 = edge.getFirstVertex();
                } else {
                    v1 = edge.getSecondVertex();
                }

                if (!visited.contains(v1)) {
                    visited.add(v1);
                    queue.add(v1);
                }
            }
        }

        return visited;
    }
}
