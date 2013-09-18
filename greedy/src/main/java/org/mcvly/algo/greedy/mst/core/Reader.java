package org.mcvly.algo.greedy.mst.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: mcvly
 * @since: 9/13/13
 */
public class Reader {

    public static UndirectedGraph readFromFileInClasspath(String fileName) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(Reader.class.getClassLoader().getResourceAsStream(fileName)))) {
                String s = r.readLine();
                // first line [number_of_nodes] [number_of_edges]
                String[] counts = s.split(" ");
                if (counts.length != 2) {
                    throw new IOException("wrong format of count");
                }
                int nodesCount = Integer.parseInt(counts[0]);
                int edgesCount = Integer.parseInt(counts[1]);

                Set<Vertex> vertices = new HashSet<>(nodesCount);
                Set<UndirectedEdge> edges = new HashSet<>(edgesCount);

                for (int i = 0; i < edgesCount; i++) {
                    s = r.readLine();
                    String[] stringPoints = s.split(" ");
                    if (stringPoints.length != 3) {
                        throw new IOException("wrong points format");
                    }

                    int cost = Integer.parseInt(stringPoints[2]);

                    Vertex vertex1 = new Vertex(stringPoints[0]);
                    Vertex vertex2 = new Vertex(stringPoints[1]);
                    UndirectedEdge edge = new UndirectedEdge(cost, vertex1, vertex2);

                    vertices.add(vertex1);
                    vertices.add(vertex2);
                    edges.add(edge);
                }

                UndirectedGraph graph = new UndirectedGraph();
                graph.setEdges(edges);
                graph.setVertices(vertices);

                return graph;
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        UndirectedGraph graph = readFromFileInClasspath("simple_graph.txt");
        System.out.println(graph.getEdges().size());
        System.out.println(graph.getVertices().size());
        System.out.println(graph);
    }
}
