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
    private Heap<Vertex> heap;

    /**
     * Cormen's Dijkstra's Algorithm implementation
     */
    public void dijkstra(Graph g, Vertex source) {
        this.graph = g;
        this.source = source;
        heap = new Heap<>();
        heap.heapify(g.getVertices());
        heap.setKey(source, 0d);

        //List<Vertex> S = new ArrayList<Vertex>();
        while (heap.size() != 0) {
            Vertex u = heap.extractMin();
            //S.add(u);
            for (Edge e : u.getAdjacents()) {
                Vertex v = e.getTarget();
                //relaxing v
                double distanceThrouU = heap.getKey(u) + e.getWeight();
                if (distanceThrouU < heap.getKey(v)) {
                    v.setPrevious(u);
                    heap.decreaseKey(v, distanceThrouU);
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

    public double getVertexDistance(Vertex target) {
        return heap.getKey(target);
    }
}
