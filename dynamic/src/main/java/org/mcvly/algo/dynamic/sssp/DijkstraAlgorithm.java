package org.mcvly.algo.dynamic.sssp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.mcvly.algo.dynamic.apsp.graph.AdjacencyGraph;
import org.mcvly.algo.dynamic.apsp.graph.DirectedGraphReader;
import org.mcvly.algo.dynamic.apsp.graph.Edge;
import org.mcvly.algo.dynamic.apsp.graph.GraphFactory;
import org.mcvly.algo.dynamic.apsp.graph.Vertex;

/**
 * @author: mcvly
 * @since: 10/5/13
 */
public class DijkstraAlgorithm<T> {
    private AdjacencyGraph<T> graph;
    private Map<Vertex<T>, Vertex<T>> pred;
    private PriorityQueue<Vertex<T>> heap;
    private Map<Vertex<T>, Double> lowestPenalties = new HashMap<>();

    public DijkstraAlgorithm(AdjacencyGraph<T> graph) {
        this.graph = graph;
        pred = new HashMap<>((int) (graph.getVertexCount() / 0.75));
        /* Compares penalties between two possible destinations. */
        heap = new PriorityQueue<>(graph.getVertexCount(), new Comparator<Vertex<T>>() {
            public int compare(Vertex<T> left, Vertex<T> right) {
                double leftPenalty = getLowestPenalty((Vertex) left);
                double rightPenalty = getLowestPenalty((Vertex) right);
                if (leftPenalty < rightPenalty) {
                    return -1;
                } else if (leftPenalty == rightPenalty) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    /**
     * Cormen's Dijkstra's Algorithm implementation
     */
    public void runAlgorithm(Vertex<T> source) {
        pred.clear();
        heap.clear();
        heap.addAll(graph.getVertices());
        setShortestDistance(source, 0);

        while (heap.size() != 0) {
            Vertex<T> u = heap.poll();
            for (Edge<T> e : graph.adj(u)) {
                Vertex<T> v = e.getTo();
                //relaxing v
                double shortDist = getLowestPenalty(u) + e.getCost();
                if (shortDist < getLowestPenalty(v)) {
                    pred.put(v, u);
                    setShortestDistance(v, shortDist);
                }
            }
        }
    }

    public List<Vertex<T>> getPathTo(Vertex<T> source, Vertex<T> target) {
        List<Vertex<T>> res = new ArrayList<>();
        Vertex<T> w = pred.get(target);
        if (!w.equals(source)) {
            res.add(w);
            res.addAll(getPathTo(source, w));
        }

        return res;
    }

    public double getVertexDistance(Vertex<T> target) {
        return lowestPenalties.get(target);
    }

    private void setShortestDistance(Vertex<T> vertex, double distance) {
        //Remove so it is inserted at the right position after the lowest penalty changes for this
        //vertex.
        heap.remove(vertex);

        //Update the lowest penalty.
        lowestPenalties.put(vertex, distance);

        //Insert the vertex again at the new position based on the lowest penalty
        heap.add(vertex);
    }

    public double getLowestPenalty(Vertex vertex) {
        Double d = lowestPenalties.get(vertex);
        return (d == null) ? Double.POSITIVE_INFINITY : d.doubleValue();
    }

    public static void main(String[] args) throws IOException {
        String fileName = "graphs/mediumEWD.txt";
        //String fileName = "graphs/g3.txt"; // -19
        AdjacencyGraph<Integer> graph = (AdjacencyGraph<Integer>) DirectedGraphReader.readIntGraph(fileName, GraphFactory.Graphs.ADJACENCY);
        DijkstraAlgorithm<Integer> algorithm = new DijkstraAlgorithm<>(graph);
        algorithm.runAlgorithm(new Vertex<>(0));
        System.out.println(algorithm.getVertexDistance(new Vertex<>(4)));
        System.out.println(algorithm.getPathTo(new Vertex<>(0), new Vertex<>(4)));
    }
}
