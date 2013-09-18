package org.mcvly.algo.greedy.mst.kruskal;

import org.mcvly.algo.greedy.mst.core.Reader;
import org.mcvly.algo.greedy.mst.core.UndirectedEdge;
import org.mcvly.algo.greedy.mst.core.UndirectedGraph;

import java.util.*;

/**
 * @author: mcvly
 * @since: 9/18/13
 */
public abstract class AbstractKruskalAlgorithm {

    public UndirectedGraph minimumSpanningTree(UndirectedGraph g) {
        List<UndirectedEdge> sortedEdges = sortEdgesByCost(g.getEdges());
        Set<UndirectedEdge> edgesInTree = new HashSet<>();

        for (UndirectedEdge edge : sortedEdges) {
            System.out.println(edge);
            if (!doesCreateCycle(edge, edgesInTree)) {
                System.out.println("included");
                edgesInTree.add(edge);
            }
            System.out.println();
        }


        UndirectedGraph result = new UndirectedGraph();
        result.setEdges(edgesInTree);

        return result;
    }

    protected abstract boolean doesCreateCycle(UndirectedEdge edge, Set<UndirectedEdge> graph);

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

}
