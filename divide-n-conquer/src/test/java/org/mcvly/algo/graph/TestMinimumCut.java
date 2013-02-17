package org.mcvly.algo.graph;

import org.junit.Test;
import org.mcvly.algo.graph.cut.ContractionAlgorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class TestMinimumCut {

    @Test
    public void testCut() throws IOException {
        String filePath = TestReadGraph.class.getClassLoader().getResource("kargerMinCut.txt").getFile();
        GraphReader reader = new GraphReader();
        Graph<SimpleVertex> g = reader.readFromFile(filePath);

        ContractionAlgorithm algorithm = new ContractionAlgorithm();

        int nTries = (int) Math.pow(g.getVertices().size(), 2);
        int[] arrayResults = new int[nTries];
        for (int i =0; i<nTries; i++) {
            arrayResults[i] = algorithm.findMinimumCutSize(g);
        }

        Map<Integer, Integer> results = new HashMap<Integer, Integer>();
        for (int r : arrayResults) {
            int rCount = results.get(r) == null ? 0 : results.get(r);
            results.put(new Integer(r), rCount+1);
        }
        System.out.println(results);
    }
}
