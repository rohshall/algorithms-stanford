package org.mcvly.algo.dynamic.apsp.graph;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 04.10.13
 */
public class DirectedGraphReader {

    public static DirectedGraph<Integer> readIntGraph(String fileName, GraphFactory.Graphs type) throws IOException {

        try (Scanner scanner = new Scanner(DirectedGraphReader.class.getClassLoader().getResourceAsStream(fileName))) {

            scanner.useLocale(Locale.ENGLISH);

            int nodesCount = scanner.nextInt();
            int edgesCount = scanner.nextInt();

            DirectedGraph<Integer> graph = GraphFactory.createGraph(nodesCount, edgesCount, type);

            while (scanner.hasNextInt()) {

                int v1 = scanner.nextInt();
                int v2 = scanner.nextInt();
                double cost = scanner.nextDouble();

                graph.addEdge(new Vertex<>(v1), new Vertex<>(v2), cost);
            }

            return graph;
        }
    }
}
