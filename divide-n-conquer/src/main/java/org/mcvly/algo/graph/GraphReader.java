package org.mcvly.algo.graph;

import java.io.*;
import java.util.*;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class GraphReader {

    private Set<SimpleVertex> vertices;
    private Set<Edge<SimpleVertex>> edges;

    public GraphReader() {
        vertices = new HashSet<SimpleVertex>();
        edges = new HashSet<Edge<SimpleVertex>>();
    }

    public Graph<SimpleVertex> readFromFile(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String s = in.readLine();

        while(s != null) {
            readGraphByLine(s);
            s = in.readLine();
        }

        in.close();
        is.close();

        return new Graph<SimpleVertex>(new ArrayList<SimpleVertex>(vertices), new ArrayList<Edge<SimpleVertex>>(edges));
    }

    private void readGraphByLine(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length == 0) {
            return;
        }
        SimpleVertex readVertex = new SimpleVertex(tokens[0]);
        if (!vertices.contains(readVertex)) {
            vertices.add(readVertex);
        }
        SimpleVertex nextVertex;
        int edgeCount = 0;
        for (int i=1; i<tokens.length; i++) {
            nextVertex = new SimpleVertex(tokens[i]);
            if (!vertices.contains(nextVertex)) {
                vertices.add(nextVertex);
            }

            Edge<SimpleVertex> edge = new Edge<SimpleVertex>(readVertex, nextVertex, String.valueOf(edgeCount));

            if (!edges.contains(edge)) {
                edges.add(edge);
            }
        }
    }
}
