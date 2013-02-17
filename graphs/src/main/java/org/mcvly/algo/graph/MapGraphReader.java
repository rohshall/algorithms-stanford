package org.mcvly.algo.graph;

import java.io.*;
import java.util.*;

/**
 * User: Ruslan
 * Date: 17.02.13
 * Time: 13:53
 */
public class MapGraphReader {

    private Map<Vertex, List<Vertex>> graph;

    public Map<Vertex, List<Vertex>> readGraph(String fileName) throws IOException {

        graph = new HashMap<Vertex, List<Vertex>>();
        InputStream is = new FileInputStream(fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String s = in.readLine();

        while(s != null) {
            readGraphByLine(s);
            s = in.readLine();
        }

        in.close();
        is.close();

        return graph;
    }

    private void readGraphByLine(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length == 0) {
            return;
        }
        Vertex readVertex = new Vertex(tokens[0]);

        List<Vertex> edgedVertices = new LinkedList<Vertex>();

        for (int i=1; i<tokens.length; i++) {
            edgedVertices.add(new Vertex(tokens[i]));
        }
//        Map<Vertex, List<Vertex>> graph = new HashMap<Vertex, List<Vertex>>();
//        Graph<Vertex> originalGraph = new GraphReader().readFromFile(fileName);
//        for (Vertex v : originalGraph.getVertices()) {
//            graph.put(v, new LinkedList<Vertex>());
//        }
//        for (Edge<Vertex> element : originalGraph.getEdges()) {
//            graph.get(element.getA()).add(element.getB());
//        }
//        return graph;
        graph.put(readVertex, edgedVertices);
    }
}
