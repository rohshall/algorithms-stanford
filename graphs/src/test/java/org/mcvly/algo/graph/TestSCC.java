package org.mcvly.algo.graph;

import junit.framework.Assert;
import org.junit.Test;
import org.mcvly.algo.graph.scc.KosarajuAlgorithm;

import java.io.IOException;

/**
 * User: RMalyona
 * Date: 20.02.13
 */
public class TestSCC {
    @Test
    public void testSCCReference() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/referenceSCC.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("3,3,3,0,0", algorithm.computeSCC(g));
    }

    @Test
    public void test11Vert() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/11vert.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("4,3,3,1,0", algorithm.computeSCC(g));
    }

    @Test
    public void test8Vert() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/8vert.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("3,3,2,0,0", algorithm.computeSCC(g));
    }

    @Test
    public void test12Vert() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/12vert.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("6,3,2,1,0", algorithm.computeSCC(g));
    }

    @Test
    public void test16Vert() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/16vert.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("8,5,2,1,0", algorithm.computeSCC(g));
    }

    @Test
    public void test50Vert() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/50vert.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("35,7,1,1,1", algorithm.computeSCC(g));
    }

    @Test
    public void test50Vert2() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/50vert2.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("36,7,1,1,1", algorithm.computeSCC(g));
    }

    @Test
    public void testAssignment() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("scc/SCC.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        Assert.assertEquals("434821,968,459,313,211", algorithm.computeSCC(g));
    }
}
