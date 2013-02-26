package org.mcvly.algo.graph.dijsktra;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: RMalyona
 * Date: 26.02.13
 */
public class Reader {
    private List<Vertex> vertices;

    private VerticesPool verticesPool;

    public Reader() {
        vertices = new ArrayList<Vertex>();
    }

    public Graph readFromFile(String fileName) throws IOException {
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

        return new Graph(vertices);
    }

    private void readGraphByLine(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length == 0) {
            return;
        }

        Edge[] adjacents = new Edge[tokens.length-1];
        for (int i=1; i<tokens.length; i++) {
            String[] tuple = tokens[i].split(",");
            Vertex target = verticesPool.add(tuple[0]);
            double weight = Double.parseDouble(tuple[1]);
            Edge e = new Edge(target, weight);
            adjacents[i-1] = e;
        }

        Vertex source = verticesPool.add(tokens[0]);
        source.setAdjacents(adjacents);
        vertices.add(source);
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
