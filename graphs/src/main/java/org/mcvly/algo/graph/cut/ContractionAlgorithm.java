package org.mcvly.algo.graph.cut;

import org.mcvly.algo.graph.Edge;
import org.mcvly.algo.graph.Graph;
import org.mcvly.algo.graph.Vertex;

import java.util.*;

/**
 * User: RMalyona
 * Date: 14.02.13
 */
public class ContractionAlgorithm {

    private List<Edge> edges;
    private Map<String, SuperNode> map;

    public int findMinimumCutSize(Graph<Vertex,Edge> graph) {

        map = new HashMap<String, SuperNode>();
        List<SuperNode> nodes = new ArrayList<SuperNode>(graph.getVertices().size());
        edges = new ArrayList<Edge>(graph.getEdges().size());

        for (Vertex v : graph.getVertices()) {
            SuperNode node = new SuperNode(v);
            map.put(v.getId(), node);
            nodes.add(node);
        }

        for (Edge edge : graph.getEdges()) {
            edges.add(new Edge(((Vertex)edge.getA()).getId(), ((Vertex)edge.getB()).getId(), edge.getId()));
        }

        //main algorithm
        while (nodes.size() > 2) {
            int edgeIndex = getNextEdge();
            SuperNode vertexToAbsorb = map.get(edges.get(edgeIndex).getB());
            SuperNode superVertex = map.get(edges.get(edgeIndex).getA());

            superVertex.absorbVertex(vertexToAbsorb);
            for (Map.Entry<String, SuperNode> entry : map.entrySet()) {
                if (entry.getValue().isVertexSame(vertexToAbsorb)) {
                    entry.setValue(superVertex);
                }
            }

            // replace vertexToAbsorb to superVertex in edges. also remove self-loop edges
            Iterator<Edge> i = edges.iterator();
            while (i.hasNext()) {
                Edge edge = i.next(); // must be called before you can call i.remove()

                //remove self-loop
                if (map.get(edge.getA()).isVertexSame(map.get(edge.getB()))) {
                    i.remove();
                }
            }

            nodes.remove(vertexToAbsorb);
        }

        // left only edges between 2 cuts
        return edges.size();
    }

    public Collection<SuperNode> getMinimumCutVertices() {
        return new HashSet<SuperNode>(map.values());
    }

    public List<Edge> getMinimumCutEdges() {
        return edges;
    }

    private int getNextEdge() {
        return new Random().nextInt(edges.size());
    }
}
