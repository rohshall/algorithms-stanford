package org.mcvly.algo.graph;

import junit.framework.Assert;
import org.junit.Test;
import org.mcvly.algo.graph.dijsktra.Vertex;
import org.mcvly.algo.graph.dijsktra.Graph;
import org.mcvly.algo.graph.dijsktra.Reader;
import org.mcvly.algo.graph.dijsktra.DijkstraAlgorithm;

import java.io.IOException;
import java.util.List;

/**
 * User: RMalyona
 * Date: 26.02.13
 */
public class TestDijsktra {

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
        algorithm.computeShortestPaths(g, g.getVertexByName("1"));
        StringBuilder result = new StringBuilder();
        result.append("{0,");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("2")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("3")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("4")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("5")))));
        result.append("}");

        Assert.assertEquals("{0,10,50,30,60}", result.toString());
    }

    @Test
    public void testSimple2() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/small2.txt").getFile();
        Reader reader = new Reader();
        Graph g = reader.readFromFile(filePath);

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        algorithm.computeShortestPaths(g, g.getVertexByName("1"));
        StringBuilder result = new StringBuilder();
        result.append("{0,");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("2")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("3")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("4")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("5")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("6")))));
        result.append("}");


        algorithm.computeShortestPaths(g, g.getVertexByName("6"));
        result = new StringBuilder();
        result.append("{");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("1")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("2")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("3")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("4")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("5")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("6")))));
        result.append("}");

        Assert.assertEquals("{1000000,23,1000000,3,33,0}", result.toString());
    }

    @Test
    public void testPreAssignment() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/dijkstraData.txt").getFile();
        Reader reader = new Reader();
        Graph g = reader.readFromFile(filePath);

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        algorithm.computeShortestPaths(g, g.getVertexByName("1"));
        StringBuilder result = new StringBuilder();
        result.append("{0,");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("2")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("3")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("4")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("5")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("6")))));
        result.append("}");

        Assert.assertEquals("{0,2971,2644,3056,2525,2818}", result.toString());

        Assert.assertEquals("2060", getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("200")))));
    }

    @Test
    public void testAssignment() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("dijkstra/dijkstraData.txt").getFile();
        Reader reader = new Reader();
        Graph g = reader.readFromFile(filePath);

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
        algorithm.computeShortestPaths(g, g.getVertexByName("1"));
        StringBuilder result = new StringBuilder();
        //result.append("{0,");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("7")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("37")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("59")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("82")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("99")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("115")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("133")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("165")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("188")))) + ",");
        result.append(getDoubleFormatted(getTotalDistance(algorithm.getPathToSource(g.getVertexByName("197")))));
        //result.append("}");

        Assert.assertEquals("2599,2610,2947,2052,2367,2399,2029,2442,2505,3068", result.toString());

    }

    private double getTotalDistance(List<Vertex> list) {
        if (list == null) {
            return 1000000;
        } else {
            return list.get(list.size()-1).getMinDistance();
        }
    }

    private String getDoubleFormatted(double d) {
        if(d == (int) d)
            return String.format("%d",(int)d);
        else
            return String.format("%s",d);
    }

}
