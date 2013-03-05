package org.mcvly.algo.graph;

import junit.framework.Assert;
import org.junit.Test;
import org.mcvly.algo.graph.dijsktra.Graph;
import org.mcvly.algo.graph.dijsktra.Reader;
import org.mcvly.algo.graph.dijsktra.DijkstraAlgorithm;

import java.io.IOException;

/**
 * User: RMalyona
 * Date: 26.02.13
 */
public class TestDijkstra {

    @Test
    public void testReadGraph() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/small1.txt").getFile();
        Reader reader = new Reader();
        Graph g = reader.readFromFile(filePath);
        Assert.assertEquals(5, g.size());
    }

    @Test
    public void testSimple1() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/small1.txt").getFile();
        Reader reader = new Reader();
        Graph g = reader.readFromFile(filePath);

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        algorithm.dijkstra(g, g.getVertexByName("1"));
        StringBuilder result = new StringBuilder();
        result.append("{0,");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("2"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("3"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("4"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("5"))));
        result.append("}");

        Assert.assertEquals("{0,10,50,30,60}", result.toString());
    }

    @Test
    public void testSimple2() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/small2.txt").getFile();
        Reader reader = new Reader();
        Graph g = reader.readFromFile(filePath);

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        algorithm.dijkstra(g, g.getVertexByName("1"));
        StringBuilder result = new StringBuilder();
        result.append("{0,");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("2"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("3"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("4"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("5"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("6"))));
        result.append("}");


        algorithm.dijkstra(g, g.getVertexByName("6"));
        result = new StringBuilder();
        result.append("{");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("1"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("2"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("3"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("4"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("5"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("6"))));
        result.append("}");

        Assert.assertEquals("{Infinity,23,Infinity,3,33,0}", result.toString());
    }

    @Test
    public void testPreAssignment() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/dijkstraData.txt").getFile();
        Reader reader = new Reader();
        Graph g = reader.readFromFile(filePath);

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        algorithm.dijkstra(g, g.getVertexByName("1"));
        StringBuilder result = new StringBuilder();
        result.append("{0,");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("2"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("3"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("4"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("5"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("6"))));
        result.append("}");

        Assert.assertEquals("{0,2971,2644,3056,2525,2818}", result.toString());

        Assert.assertEquals("2060", getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("200"))));
    }

    @Test
    public void testAssignment() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/dijkstraData.txt").getFile();
        Reader reader = new Reader();
        long start = System.currentTimeMillis();
        Graph g = reader.readFromFile(filePath);
        long stop = System.currentTimeMillis();
        System.out.println("Read time " + (stop-start) );

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        start = System.currentTimeMillis();
        algorithm.dijkstra(g, g.getVertexByName("1"));
        stop = System.currentTimeMillis();
        System.out.println("Algorithm " + (stop-start) );
        start = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        //result.append("{0,");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("7"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("37"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("59"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("82"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("99"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("115"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("133"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("165"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("188"))) + ",");
        result.append(getDoubleFormatted(algorithm.getVertexDistance(g.getVertexByName("197"))));
        //result.append("}");
        stop = System.currentTimeMillis();
        System.out.println("Results " + (stop-start));
        Assert.assertEquals("2599,2610,2947,2052,2367,2399,2029,2442,2505,3068", result.toString());

    }

    private String getDoubleFormatted(double d) {
        if(d == (int) d)
            return String.format("%d",(int)d);
        else
            return String.format("%s",d);
    }

}
