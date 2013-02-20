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
        first.setAttribute("explored", true);
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(first);
        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            System.out.println("\n" + v);
            List<Vertex> adjacentVertices = g.getAdjacentVertices(v);
            for (Vertex w : adjacentVertices) {
                if (w.getAttribute("explored") == null || Boolean.valueOf(w.getAttribute("explored").toString()) == Boolean.FALSE) {
                    w.setAttribute("explored", true);
                    queue.add(w);
                    System.out.println(w);
                }
            }
        }
    }
}
