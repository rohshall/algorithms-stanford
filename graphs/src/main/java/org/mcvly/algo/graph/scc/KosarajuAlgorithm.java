package org.mcvly.algo.graph.scc;

import org.mcvly.algo.dnq.selection.AbstractLinearSelection;
import org.mcvly.algo.dnq.selection.RandomizedSelect;
import org.mcvly.algo.graph.MapGraph;
import org.mcvly.algo.graph.MapGraphReader;
import org.mcvly.algo.graph.Vertex;

import java.io.IOException;
import java.util.*;

/**
 * User: RMalyona
 * Date: 20.02.13
 */
public class KosarajuAlgorithm {
    private MapGraph graph;
    private MapGraph reversedGraph;
    private Map<Vertex, List<Vertex>> sccs;
    private Vertex[] finishingTime;
    private boolean firstIteration = false;

    /**
     * number of nodes processed so far
     */
    private int t;

    /**
     * current source vertex
     */
    private Vertex s = null;

    public String computeSCC(MapGraph g) {
        this.graph = g;
        this.reversedGraph = graph.getReversedGraph();
        sccs = new HashMap<Vertex, List<Vertex>>();
        finishingTime = new Vertex[reversedGraph.size()];

        firstDFSLoop(reversedGraph);
        clearMarks();
        secondDFSLoop(graph);

        return getFiveBiggestSCC();
    }

    /**
     * first loop computes finishing time for nodes
     * @param g
     */
    private void firstDFSLoop(MapGraph g) {
        firstIteration = true;
        t = 0;
        for (Vertex w : g.keySet()) {
            if (!w.isVisited()) {
                dfs(g, w);
            }
        }
    }

    private void secondDFSLoop(MapGraph g) {
        firstIteration = false;
        s = null;
        for (int i=finishingTime.length-1; i>-1; --i) {
            Vertex w = finishingTime[i];
            if (!w.isVisited()) {
                s = w;
                dfs(g, s);
            }
        }
    }

    private void dfs(MapGraph g, Vertex w) {
        w.setVisited(true);
        if (!firstIteration) {
            if (sccs.get(s) == null) {
                sccs.put(s, new LinkedList<Vertex>());
            }
            sccs.get(s).add(w);
        }
        if (g.getAdjacentVertices(w) != null) {
            for (Vertex v : g.getAdjacentVertices(w)) {
                if (!v.isVisited()) {
                    dfs(g,v);
                }
            }
        }
        if (firstIteration) {
            t++;
            finishingTime[t-1] = w;
        }
    }

    private void clearMarks() {
        for (int i=0; i<finishingTime.length; i++) {
            finishingTime[i].setVisited(false);
        }
    }

    private String getFiveBiggestSCC() {
        int[] sccSizes = new int[sccs.size()];
        int i = 0;
        for (List<Vertex> list : sccs.values()) {
            sccSizes[i++] = list.size();
        }

        StringBuilder b = new StringBuilder();
        AbstractLinearSelection selection = new RandomizedSelect();
        b.append(selection.getOrderedStatistic(sccSizes, sccSizes.length)).append(",");
        b.append(selection.getOrderedStatistic(sccSizes, sccSizes.length-1)).append(",");
        b.append(selection.getOrderedStatistic(sccSizes, sccSizes.length-2)).append(",");
        b.append(selection.getOrderedStatistic(sccSizes, sccSizes.length-3)).append(",");
        b.append(selection.getOrderedStatistic(sccSizes, sccSizes.length-4));
        return b.toString();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String filePath = KosarajuAlgorithm.class.getResource("SCC.txt").getFile();
        MapGraphReader reader = new MapGraphReader();
        MapGraph g = reader.readGraph(filePath);

        long start = System.currentTimeMillis();
        KosarajuAlgorithm algorithm = new KosarajuAlgorithm();
        System.out.println(algorithm.computeSCC(g));
        long finished = System.currentTimeMillis();
        System.out.println((finished-start));
    }
}
