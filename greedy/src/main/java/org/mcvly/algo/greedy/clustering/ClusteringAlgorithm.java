package org.mcvly.algo.greedy.clustering;

import org.mcvly.algo.greedy.mst.core.Reader;
import org.mcvly.algo.greedy.mst.core.UndirectedEdge;
import org.mcvly.algo.greedy.mst.core.UndirectedGraph;
import org.mcvly.algo.greedy.mst.core.Vertex;
import org.mcvly.algo.greedy.mst.kruskal.UnionFindImplementation;

import java.util.List;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 18.09.13
 */
public class ClusteringAlgorithm extends UnionFindImplementation {

    public int kClustering(UndirectedGraph g, int k) {

        List<UndirectedEdge> sortedEdges = sortEdgesByCost(g.getEdges());
        UndirectedGraph result = new UndirectedGraph();
        for (Vertex v : g.getVertices()) {
            union.put(v, new Union(v));
        }

        int n = g.getVertices().size();

        for (UndirectedEdge edge : sortedEdges) {

            if (!doesCreateCycle(edge, result)) {
                if (n > k) {
                    addEdgeToGraph(result, edge);
                    n--;
                } else {
                    return edge.getCost();
                }

            }
        }

        return -1;
    }


    public static void main(String[] args) {
        UndirectedGraph graph = Reader.readFromFileInClasspath("clustering1.txt");
        ClusteringAlgorithm algorithm = new ClusteringAlgorithm();

        System.out.println(algorithm.kClustering(graph, 4));
    }

}
