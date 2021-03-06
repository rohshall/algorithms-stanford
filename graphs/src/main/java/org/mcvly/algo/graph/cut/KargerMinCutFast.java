package org.mcvly.algo.graph.cut;

import org.mcvly.algo.graph.MapGraph;
import org.mcvly.algo.graph.Vertex;

import java.util.*;

/**
 * User: Ruslan
 * Date: 17.02.13
 * Time: 13:44
 */
public class KargerMinCutFast {
    private MapGraph graph;
    private Map<Vertex, Set<Vertex>> absorbedVertices;

    public int findMinimumCutSize(MapGraph data) {
        if (data != null && data.size() > 0) {
            graph = new MapGraph(data);
        } else {
            throw new IllegalArgumentException("wrong graph!");
        }

        absorbedVertices = new HashMap<Vertex, Set<Vertex>>();
        for (Vertex v : graph.getVertices()) {
            absorbedVertices.put(v, new HashSet<Vertex>());
        }

        //main algorithm
        while (graph.size() > 2) {
            Vertex superVertex = getNextVertex();
            if (graph.getAdjacentVertices(superVertex).length == 0) {
                break;
            }
            Vertex vertexToAbsorb = graph.getAdjacentVertices(superVertex)[0];

            if (graph.getAdjacentVertices(vertexToAbsorb) == null) {
                break;
            }

            // put to superVertex absorbedVertex and its absorbed vertices
            absorbedVertices.get(superVertex).add(vertexToAbsorb);
            absorbedVertices.get(superVertex).addAll(absorbedVertices.get(vertexToAbsorb));

            // put edges of absorbedVertex to superVertex
            List<Vertex> list1 = Arrays.asList(graph.getAdjacentVertices(superVertex));
            list1.addAll(Arrays.asList(graph.getAdjacentVertices(vertexToAbsorb)));
            graph.setAdjacentVertices(superVertex, list1.toArray(new Vertex[0]));

            // replace absorbedVertex to superVertex in edges, also remove self-loops
            for (Map.Entry<Vertex, Vertex[]> entry: graph.entrySet()) {
                List<Vertex> list = new LinkedList<Vertex>(Arrays.asList(entry.getValue()));
                ListIterator<Vertex> i = list.listIterator();
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
                entry.setValue(list.toArray(new Vertex[0]));
            }
            graph.removeVertex(vertexToAbsorb);
        }

        int edgesLeft = 0;
        for (Vertex v : graph.getVertices()) {
            edgesLeft += graph.getAdjacentVertices(v).length;
            break;
        }
        return edgesLeft;
    }

    private Vertex getNextVertex() {
        List<Vertex> keyList = new ArrayList<Vertex>(graph.getVertices());
        int randomIndex = new Random().nextInt(keyList.size());
        return keyList.get(randomIndex);
    }
}
