package org.mcvly.algo.graph.scc;

import org.mcvly.algo.graph.MapGraph;
import org.mcvly.algo.graph.Vertex;

import java.util.*;

/**
 * User: RMalyona
 * Date: 20.02.13
 */
public class KosarajuAlgorithm {
    private MapGraph graph;
    private MapGraph reversedGraph;
    private Map<Vertex, Vertex> leaders;
    private Vertex[] finishingTime;
    private boolean firstIteration = false;

    Map<Vertex,List<Vertex>> sccs;

    /**
     * number of nodes processed so far
     */
    private int t;

    /**
     * current source vertex
     */
    private Vertex s = null;

    //TODO optimize this later
    public String computeSCC(MapGraph graph) {
        this.graph = graph;
        this.reversedGraph = graph.getReversedGraph();
        leaders = new HashMap<Vertex, Vertex>(graph.size());
        finishingTime = new Vertex[graph.size()];

        firstDFSLoop(reversedGraph);
        clearMarks();
        secondDFSLoop(graph);

        sccs = new HashMap<Vertex, List<Vertex>>();
        fillSCCs();
        ValueComparator bvc =  new ValueComparator(sccs);
        TreeMap<Vertex,List<Vertex>> sorted_map = new TreeMap<Vertex,List<Vertex>>(bvc);
        sorted_map.putAll(sccs);

        return getFiveBiggestSCC(sorted_map);
    }

    /**
     * first loop computes finishing time for nodes
     * @param g
     */
    private void firstDFSLoop(MapGraph g) {
        firstIteration = true;
        t = 0;
        for (Vertex w : reversedGraph.keySet()) {
            if (w.getAttribute("explored") == null || Boolean.valueOf(w.getAttribute("explored").toString()) == Boolean.FALSE) {
                dfs(g, w);
            }
        }
    }

    private void secondDFSLoop(MapGraph g) {
        firstIteration = false;
        s = null;
        for (int i=finishingTime.length-1; i>-1; --i) {
            Vertex w = finishingTime[i];
            if (w.getAttribute("explored") == null || Boolean.valueOf(w.getAttribute("explored").toString()) == Boolean.FALSE) {
                s = w;
                dfs(g, s);
            }
        }
    }

    private void dfs(MapGraph g, Vertex w) {
        w.setAttribute("explored", true);
        if (!firstIteration) {
            leaders.put(w,s);
        }
        for (Vertex v : g.getAdjacentVertices(w)) {
            if (v.getAttribute("explored") == null || Boolean.valueOf(v.getAttribute("explored").toString()) == Boolean.FALSE) {
                dfs(g,v);
            }
        }
        if (firstIteration) {
            t++;
            finishingTime[t-1] = w;
        }
    }

    private void clearMarks() {
        for (int i=0; i<finishingTime.length; i++) {
            finishingTime[i].setAttribute("explored", false);
        }
    }

    private void fillSCCs() {
        for (Map.Entry<Vertex,Vertex> entry : leaders.entrySet()) {
            if (!sccs.containsKey(entry.getValue())) {
                sccs.put(entry.getValue(), new ArrayList<Vertex>());
            }
            sccs.get(entry.getValue()).add(entry.getKey());
        }
    }

    private class ValueComparator implements Comparator<Vertex> {

        Map<Vertex, List<Vertex>> base;
        public ValueComparator(Map<Vertex, List<Vertex>> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.
        public int compare(Vertex a, Vertex b) {
            if (base.get(a).size() >= base.get(b).size()) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }

    private String getFiveBiggestSCC(TreeMap<Vertex, List<Vertex>> sorted_map) {
        int i=0;
        StringBuilder b = new StringBuilder();
        for (Map.Entry<Vertex, List<Vertex>> entry : sorted_map.entrySet()) {
            if (i == 5) {
                break;
            }
            b.append(entry.getValue().size());
            b.append(",");
            i++;
        }
        if (i<5) {
            while(i<5) {
                b.append("0,");
                i++;
            }
        }
        b.deleteCharAt(b.length()-1);
        return b.toString();
    }
}
