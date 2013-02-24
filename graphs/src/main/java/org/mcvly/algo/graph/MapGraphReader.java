package org.mcvly.algo.graph;

import java.io.*;
import java.util.*;

import static org.mcvly.algo.graph.MapGraph.concat;

/**
 * User: Ruslan
 * Date: 17.02.13
 * Time: 13:53
 */
public class MapGraphReader {

    private Map<Vertex, Vertex[]> graph;

    private VerticesPool verticesPool;

    public MapGraph readGraph(String fileName) throws IOException {

        graph = new HashMap<Vertex, Vertex[]>();

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
        verticesPool.clear();
        verticesPool = null;
        return new MapGraph(graph);
    }

    private void readGraphByLine(String line) {
           String[] tokens = line.split("\\s+");
        if (tokens.length == 0) {
            return;
        }

        Vertex readVertex = verticesPool.add(tokens[0]);

        Vertex[] edgedVertices = new Vertex[tokens.length-1];
        for (int i=1; i<tokens.length; i++) {
            Vertex v = verticesPool.add(tokens[i]);
            if (!graph.containsKey(v)) {
                graph.put(v,null);
            }
            edgedVertices[i-1] = v;
        }

        graph.put(readVertex, concat(graph.get(readVertex), edgedVertices));
    }

    private static class VerticesPool {
        private Map<String, Vertex> vertices;

        public VerticesPool() {
            this.vertices = new WeakHashMap<String, Vertex>();
        }

        public void clear() {
            vertices.clear();
        }

        public int size() {
            return vertices.size();
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
