package org.mcvly.algo.graph.cut;

import org.mcvly.algo.graph.Vertex;

import java.util.*;

/**
 * User: Ruslan
 * Date: 17.02.13
 * Time: 13:44
 */
public class KargerMinCutFast {
    private Map<Vertex, List<Vertex>> graph;
    private Map<Vertex, Set<Vertex>> absorbedVertices;

    public int findMinimumCutSize(Map<Vertex, List<Vertex>> data) {
        if (data != null && data.size() > 0) {
            this.graph = new HashMap<Vertex, List<Vertex>>(data.size());
            for (Map.Entry<Vertex, List<Vertex>> entry: data.entrySet()) {
                graph.put(new Vertex(entry.getKey().getId()), new LinkedList<Vertex>(entry.getValue()));
            }
        } else {
            throw new IllegalArgumentException("wrong graph!");
        }

        absorbedVertices = new HashMap<Vertex, Set<Vertex>>();
        for (Vertex v : graph.keySet()) {
            absorbedVertices.put(v, new HashSet<Vertex>());
        }

        //main algorithm
        while (graph.size() > 2) {
            Vertex superVertex = getNextVertex();
            if (graph.get(superVertex).size() == 0) {
                break;
            }
            Vertex vertexToAbsorb = graph.get(superVertex).get(0);

            if (graph.get(vertexToAbsorb) == null) {
                break;
            }

            // put to superVertex absorbedVertex and its absorbed vertices
            absorbedVertices.get(superVertex).add(vertexToAbsorb);
            absorbedVertices.get(superVertex).addAll(absorbedVertices.get(vertexToAbsorb));

            // put edges of absorbedVertex to superVertex
            graph.get(superVertex).addAll(graph.get(vertexToAbsorb));

            // replace absorbedVertex to superVertex in edges, also remove self-loops
            for (Map.Entry<Vertex, List<Vertex>> entry: graph.entrySet()) {
                ListIterator<Vertex> i = entry.getValue().listIterator();
                while (i.hasNext()) {
                    Vertex v = i.next();
                    if (vertexToAbsorb.equals(v) && superVertex.equals(entry.getKey())) {
                        i.remove();
                    } else if (v.equals(vertexToAbsorb)) {
                        i.set(superVertex);
                    } else if (v.equals(entry.getKey())) {
                        i.remove();
                    }
                }
            }
            graph.remove(vertexToAbsorb);
        }

        int edgesLeft = 0;
        for (List<Vertex> list : graph.values()) {
            edgesLeft += list.size();
            break;
        }
        return edgesLeft;
    }

    private Vertex getNextVertex() {
        List<Vertex> keyList = new ArrayList<Vertex>(graph.keySet());
        int randomIndex = new Random().nextInt(keyList.size());
        return keyList.get(randomIndex);
    }
}
