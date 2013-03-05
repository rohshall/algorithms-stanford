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

        int i = 0, start=0;
        char[] array = line.toCharArray();
        while (Character.isDigit(array[i])) {
            i++;
        }
        start = i;
        Vertex source = new Vertex(line.substring(0, i));
        List<Edge> adjacents = new ArrayList<Edge>();
        boolean isSource = true;
        Vertex target = null;
        for (;i<array.length; i++) {
            if (isSource) {
                if (array[i] == ',') {
                    isSource = false;
                    target = new Vertex(line.substring(start, i));
                    start=i+1;
                } else if (array[i]==' ' || array[i]=='\t') {
                    start++;
                }
            } else {
                if (array[i]==' ' || array[i]=='\t') {
                    double weight = Double.parseDouble(line.substring(start, i));
                    isSource = true;
                    adjacents.add(new Edge(target,weight));
                    start = i+1;
                }
            }
        }

        source.setAdjacents(adjacents.toArray(new Edge[0]));
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
