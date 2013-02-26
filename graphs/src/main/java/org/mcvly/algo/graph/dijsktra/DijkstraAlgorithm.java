package org.mcvly.algo.graph.dijsktra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * User: RMalyona
 * Date: 26.02.13
 */
public class DijkstraAlgorithm {

    private Graph graph;
    private Vertex source;

    public void computeShortestPaths(Graph g, Vertex source) {
        this.graph = g;
        this.source = source;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        source.setMinDistance(0);
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex w = vertexQueue.poll();
            if (w.getAdjacents() != null) {
                for (Edge e : w.getAdjacents()) {
                    Vertex v = e.getTarget();
                    double distanceThrouW = w.getMinDistance() + e.getWeight();
                    if (distanceThrouW < v.getMinDistance()) {
                        vertexQueue.remove(v);
                        v.setMinDistance(distanceThrouW);
                        v.setPrevious(w);
                        vertexQueue.add(v);
                    }
                }
            }
        }
    }

    public List<Vertex> getPathToSource(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex!=null; vertex=vertex.getPrevious()) {
            path.add(vertex);
        }

        Collections.reverse(path);
        if (path.get(0) == source) {
            return path;
        } else {
            return null;
        }
    }
}
