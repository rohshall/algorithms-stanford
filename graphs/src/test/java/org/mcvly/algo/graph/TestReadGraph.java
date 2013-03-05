package org.mcvly.algo.graph;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class TestReadGraph {
    @Test
    public void testRead() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("graph1.txt").getFile();
        GraphReader reader = new GraphReader();
        Graph g = reader.readFromFile(filePath);
        Assert.assertEquals(4, g.getVertices().size());
        Assert.assertEquals(5, g.getEdges().size());
        System.out.println(g.toString());
    }

    @Test
    public void testReadMapGraph() throws IOException, InterruptedException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/SCC.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        long start = System.currentTimeMillis();

        MapGraph g = reader.readGraph(filePath);
        Assert.assertEquals(875714, g.size());

        long finished = System.currentTimeMillis();
        System.out.println((finished-start));
        start = System.currentTimeMillis();
        System.gc();
        Thread.sleep(3000);
        MapGraph reversedGraph = g.getReversedGraph();
        finished = System.currentTimeMillis();
        System.out.println((finished-start));
        Assert.assertEquals(875714, reversedGraph.size());
    }

    @Test
    public void test1() {
        Map<String,Set<String>> map = new HashMap<String, Set<String>>();
        for (String s : map.get("a")) {
            System.out.println();
        }
    }

}
