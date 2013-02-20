package org.mcvly.algo.graph;

import java.io.*;
import java.util.*;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class GraphReader {

    private Set<Vertex> vertices;
    private Set<Edge> edges;

    public GraphReader() {
        vertices = new HashSet<Vertex>();
        edges = new HashSet<Edge>();
    }

    public Graph<Vertex,Edge> readFromFile(String fileName) throws IOException {
        InputStream is = new FileInputStream(fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String s = in.readLine();

        while(s != null) {
            readGraphByLine(s);
            s = in.readLine();
        }

        in.close();
        is.close();

        return new Graph<Vertex,Edge>(new ArrayList<Vertex>(vertices), new ArrayList<Edge>(edges));
    }

    private void readGraphByLine(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length == 0) {
            return;
        }
        Vertex readVertex = new Vertex(tokens[0]);
        if (!vertices.contains(readVertex)) {
            vertices.add(readVertex);
        }
        Vertex nextVertex;
        int edgeCount = 0;
        for (int i=1; i<tokens.length; i++) {
            nextVertex = new Vertex(tokens[i]);
            if (!vertices.contains(nextVertex)) {
                vertices.add(nextVertex);
            }

            Edge edge = new Edge(readVertex, nextVertex, String.valueOf(edgeCount));

            if (!edges.contains(edge)) {
                edges.add(edge);
            }
        }
    }
}
