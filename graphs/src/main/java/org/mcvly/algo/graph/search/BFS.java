package org.mcvly.algo.graph.search;

import org.mcvly.algo.graph.MapGraph;
import org.mcvly.algo.graph.Vertex;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * User: RMalyona
 * Date: 19.02.13
 */
public class BFS {
    public static void traverse(MapGraph g, Vertex s) {
        Vertex first = g.getVertex(s);
        if (first == null) {
            throw new RuntimeException("Error. Wrong first vertex");
        }
        first.setVisited(true);
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(first);
        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            System.out.println("\n" + v);
            Vertex[] adjacentVertices = g.getAdjacentVertices(v);
            for (Vertex w : adjacentVertices) {
                if (!w.isVisited()) {
                    w.setVisited(true);
                    queue.add(w);
                    System.out.println(w);
                }
            }
        }
    }
}
