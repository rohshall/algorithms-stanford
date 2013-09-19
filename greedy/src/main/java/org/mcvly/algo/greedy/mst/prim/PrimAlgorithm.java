package org.mcvly.algo.greedy.mst.prim;

import org.mcvly.algo.greedy.mst.core.Reader;
import org.mcvly.algo.greedy.mst.core.UndirectedEdge;
import org.mcvly.algo.greedy.mst.core.UndirectedGraph;
import org.mcvly.algo.greedy.mst.core.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: mcvly
 * @since: 9/13/13
 */
public class PrimAlgorithm {

    public static UndirectedGraph minimumSpanningTree(UndirectedGraph g) {

        Set<UndirectedEdge> edgesInTree = new HashSet<>();
        Set<Vertex> visited = new HashSet<>();
        visited.add(g.getVertices().iterator().next());
        System.out.println("at start " + visited);
        UndirectedGraph result = new UndirectedGraph();

        //TODO: use heaps to speed up this algorithm
        while(visited.size() != g.getVertices().size()) {
            UndirectedEdge cheapest = getCheapestEdge(g.getEdges(), visited);
            System.out.println("Cheapest " + cheapest);
            result.addEdge(cheapest);
            visited.add(cheapest.getFirstVertex());
            visited.add(cheapest.getSecondVertex());
        }

        return result;
    }

    public static long sumOfEdges(UndirectedGraph g) {
        long result = 0;
        for (UndirectedEdge e : g.getEdges()) {
            result += e.getCost();
        }

        return result;
    }

    private static UndirectedEdge getCheapestEdge(Set<UndirectedEdge> edges, Set<Vertex> visited) {
        int minCost = Integer.MAX_VALUE;
        UndirectedEdge resulting = null;
        for (UndirectedEdge edge : edges) {
            if (visited.contains(edge.getFirstVertex()) && !visited.contains(edge.getSecondVertex()) ||
                    !visited.contains(edge.getFirstVertex()) && visited.contains(edge.getSecondVertex())) {
                if (edge.getCost() < minCost) {
                    minCost = edge.getCost();
                    resulting = edge;
                }
            }
        }

        return resulting;
    }

    public static void main(String[] args) {
        UndirectedGraph graph = Reader.readFromFileInClasspath("primcase.txt");
        //primcase 120971
        // simple_graph 11
        // edges  -3612829
        System.out.println(graph);
        UndirectedGraph mst = minimumSpanningTree(graph);
        System.out.println(mst);
        System.out.println("Sum: " + sumOfEdges(mst));
    }
}
