package org.mcvly.algo.dynamic.sssp;

/**
 * @author: mcvly
 * @since: 10/5/13
 */
public class DijkstraAlgorithm {
//    private AdjacencyGraph graph;
//    private int source;
//    int[] pred;
//    private PriorityQueue<Integer> heap;
//
//    /**
//     * Cormen'start Dijkstra'start Algorithm implementation
//     */
//    public void dijkstra(AdjacencyGraph g, int source) {
//        this.graph = g;
//        this.source = source;
//        heap = new PriorityQueue<>();
//        pred = new int[g.getVertexCount()];
//        heap.addAll(g.getVertices());
//        heap.setKey(source, 0d);
//
//        //List<Vertex> S = new ArrayList<Vertex>();
//        while (heap.size() != 0) {
//            int u = heap.poll();
//            //S.add(u);
//            for (Edge e : graph.adj(u)) {
//                int v = e.getTo();
//                //relaxing v
//                double distanceThrouU = heap.getKey(u) + e.getCost();
//                if (distanceThrouU < heap.getKey(v)) {
//                    pred[v] = u;
//                    heap.decreaseKey(v, distanceThrouU);
//                }
//            }
//        }
//    }
//
//    public List<Vertex> getPathToSource(Vertex target) {
//        List<Vertex> path = new ArrayList<Vertex>();
//        for (Vertex vertex = target; vertex!=null; vertex=vertex.getPrevious()) {
//            path.add(vertex);
//        }
//
//        Collections.reverse(path);
//        if (path.get(0) == source) {
//            return path;
//        } else {
//            return null;
//        }
//    }
//
//    public double getVertexDistance(Vertex target) {
//        return heap.getKey(target);
//    }


}
