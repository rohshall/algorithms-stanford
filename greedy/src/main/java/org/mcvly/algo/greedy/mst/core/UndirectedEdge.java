package org.mcvly.algo.greedy.mst.core;

/**
 * @author: mcvly
 * @since: 9/13/13
 */
public class UndirectedEdge {
    private int cost;
    private Vertex firstVertex, secondVertex;

    public UndirectedEdge(int cost, Vertex firstVertex, Vertex secondVertex) {
        this.cost = cost;
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(Vertex firstVertex) {
        this.firstVertex = firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public void setSecondVertex(Vertex secondVertex) {
        this.secondVertex = secondVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UndirectedEdge that = (UndirectedEdge) o;

        if (cost != that.cost) return false;
        if (!firstVertex.equals(that.firstVertex)) return false;
        if (!secondVertex.equals(that.secondVertex)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cost;
        result = 31 * result + firstVertex.hashCode();
        result = 31 * result + secondVertex.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return firstVertex + ":" + secondVertex + " " + cost;
    }
}
