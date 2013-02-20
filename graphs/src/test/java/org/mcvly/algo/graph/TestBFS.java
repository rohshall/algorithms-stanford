package org.mcvly.algo.graph;

import org.junit.Test;
import org.mcvly.algo.graph.search.BFS;

import java.io.IOException;

/**
 * User: RMalyona
 * Date: 19.02.13
 */
public class TestBFS {
    @Test
    public void testTraverse() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("kargerMinCut.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);


        BFS.traverse(g, new Vertex("1"));
    }
}
