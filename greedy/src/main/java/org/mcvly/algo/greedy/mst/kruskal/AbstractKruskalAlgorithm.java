package org.mcvly.algo.greedy.mst.kruskal;

import org.mcvly.algo.greedy.mst.core.Reader;
import org.mcvly.algo.greedy.mst.core.UndirectedEdge;
import org.mcvly.algo.greedy.mst.core.UndirectedGraph;
import org.mcvly.algo.greedy.mst.prim.PrimAlgorithm;

import java.util.*;

/**
 * @author: mcvly
 * @since: 9/18/13
 */
public abstract class AbstractKruskalAlgorithm {

    public UndirectedGraph minimumSpanningTree(UndirectedGraph g) {
        List<UndirectedEdge> sortedEdges = sortEdgesByCost(g.getEdges());
        UndirectedGraph result = new UndirectedGraph();

        for (UndirectedEdge edge : sortedEdges) {
            if (!doesCreateCycle(edge, result)) {
                addEdgeToGraph(result, edge);
            }
        }

        return result;
    }

    protected abstract boolean doesCreateCycle(UndirectedEdge edge, UndirectedGraph graph);

    protected void addEdgeToGraph(UndirectedGraph graph, UndirectedEdge edge) {
        graph.addEdge(edge);
    }

    protected List<UndirectedEdge> sortEdgesByCost(Set<UndirectedEdge> edges) {

        Comparator<UndirectedEdge> comparator = new Comparator<UndirectedEdge>() {
            @Override
            public int compare(UndirectedEdge o1, UndirectedEdge o2) {
                return o1.getCost() - o2.getCost();
            }
        };

        List<UndirectedEdge> sortedEdges = new ArrayList<>(edges);
        Collections.sort(sortedEdges, comparator);

        return sortedEdges;
    }

    public static void main(String[] args) {
        UndirectedGraph graph = Reader.readFromFileInClasspath("edges.txt");
        AbstractKruskalAlgorithm algorithm = new UnionFindImplementation();
        UndirectedGraph g = algorithm.minimumSpanningTree(graph);
        System.out.println(PrimAlgorithm.sumOfEdges(g));
    }

}
