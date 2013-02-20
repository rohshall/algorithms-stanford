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
    private Map<Vertex, List<Vertex>> reversedGraph;

    private VerticesPool verticesPool;

    public MapGraph readGraph(String fileName) throws IOException {

        graph = new HashMap<Vertex, List<Vertex>>();
        reversedGraph = new HashMap<Vertex, List<Vertex>>();

        verticesPool = new VerticesPool();

        InputStream is = new FileInputStream(fileName);
        BufferedReader in = new BufferedReader(new InputStreamReader(is));

        String s = in.readLine();

        while(s != null) {
            readGraphByLine(s);
            s = in.readLine();
        }

        in.close();
        is.close();

        return new MapGraph(graph);
    }

    private void readGraphByLine(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length == 0) {
            return;
        }

        Vertex readVertex = verticesPool.add(tokens[0]);
        if (graph.get(readVertex) == null) {
            graph.put(readVertex, new LinkedList<Vertex>());
        }

        List<Vertex> edgedVertices = new LinkedList<Vertex>();

        for (int i=1; i<tokens.length; i++) {
            Vertex v = verticesPool.add(tokens[i]);
            if (graph.get(v) == null) {
                graph.put(v, new LinkedList<Vertex>());
            }

            edgedVertices.add(v);
        }

        graph.get(readVertex).addAll(edgedVertices);
    }

    private static class VerticesPool {
        private Map<String, Vertex> vertices;

        public VerticesPool() {
            this.vertices = new HashMap<String, Vertex>();
        }

        public Vertex add(String id) {

            Vertex x;
            if (!vertices.containsKey(id)) {
                x = new Vertex(id);
                vertices.put(id, x);
            } else {
                x = vertices.get(id);
            }
            return x;
        }
    }
}
