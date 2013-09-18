package org.mcvly.algo.greedy.mst.kruskal;

import org.mcvly.algo.greedy.mst.core.Reader;
import org.mcvly.algo.greedy.mst.core.UndirectedEdge;
import org.mcvly.algo.greedy.mst.core.UndirectedGraph;
import org.mcvly.algo.greedy.mst.core.Vertex;

import java.util.Set;

/**
 * @author: mcvly
 * @since: 9/18/13
 */
public class StraightforwardImplementation extends AbstractKruskalAlgorithm {

    @Override
    protected boolean doesCreateCycle(UndirectedEdge candidate, Set<UndirectedEdge> graph) {
        Vertex firstMatch = null;

        //TODO: use BFS

        return false;
    }

    public static void main(String[] args) {
        UndirectedGraph graph = Reader.readFromFileInClasspath("simple_graph.txt");
        AbstractKruskalAlgorithm algorithm = new StraightforwardImplementation();
        algorithm.minimumSpanningTree(graph);

    }
}
