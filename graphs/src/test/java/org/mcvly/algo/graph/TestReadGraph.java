package org.mcvly.algo.graph;

import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

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
}
