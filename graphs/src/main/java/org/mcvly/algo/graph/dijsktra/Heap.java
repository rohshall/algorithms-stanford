package org.mcvly.algo.graph.dijsktra;

import java.util.*;

/**
 * User: RMalyona
 * Date: 02.03.13
 */
public class Heap {
    private ArrayList<Vertex> array;
    Map<Vertex, Integer> indices;
    Map<Vertex, Double> keys;

    public void heapify(Vertex[] a) {
        List<Vertex> list = new ArrayList<Vertex>(a.length);
        Collections.addAll(list, a);
        heapify(list);
    }

    public void heapify(List<Vertex> list) {
        this.array = new ArrayList<Vertex>(list);
        indices = new HashMap<Vertex, Integer>(array.size());
        keys = new HashMap<Vertex, Double>(array.size());

        for (int i= array.size()/2; i >=0; --i) {
            minHeapify(i);
        }
        int i = 0;
        for (Vertex v : array) {
            indices.put(v, i++);
            keys.put(v, Double.POSITIVE_INFINITY);
        }
    }

    public void minHeapify(Vertex v) {
        if (indices.containsKey(v)) {
            minHeapify(indices.get(v));
        }
    }

    /**
     * maintains heap property
     * @param i index in array, who possibly violates heap property
     */
    private void minHeapify(int i) {
        int largest = i;
        if (left(i) < array.size() && getKey(array.get(left(i))) < getKey(array.get(i))) {
            largest = left(i);
        } else {
            largest = i;
        }
        if (right(i) < array.size() && getKey(array.get(right(i))) < getKey(array.get(largest))) {
            largest = right(i);
        }
        if (largest != i) {
            swap(i, largest);
            minHeapify(largest);
        }
    }

    public Vertex extractMin() {
        if (array.size() < 1) {
            throw new RuntimeException("heap underflow");
        }
        Vertex min = array.get(0);
        swap(0, array.size()-1);
        array.remove(array.size()-1);
        minHeapify(0);
        return min;
    }

    public void insert(Vertex v) {
        array.add(v);
        indices.put(v, array.size() - 1);
        decreaseKey(v, getKey(v));
    }

    public void decreaseKey(Vertex v, double key) {
        if (!keys.containsKey(v)) {
            throw new RuntimeException("Key not found");
        }
        if (getKey(v) < key) {
            throw new IllegalArgumentException("desired key is bigger than given");
        }
        if (indices.containsKey(v)) {
            keys.put(v, key);
            int i = indices.get(v);
            while (i>0 && parent(i)>=0 && getKey(array.get(parent(i))) > getKey(array.get(i))) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

    }

    private void swap(int i, int j) {
        Vertex t = array.get(i);
        array.set(i, array.get(j));
        indices.put(array.get(i), i);

        array.set(j, t);
        indices.put(t, j);
    }

    public Vertex get(int i) {
        return array.get(i);
    }

    public int size() {
        return array.size();
    }

    public int parent(int i) {
        if (i % 2 == 1) {
            i++;
        }
        return i/2 -1;
    }

    public int left(int i) {
        return 2*i +1;
    }

    public int right(int i) {
        return 2*i + 2;
    }

    public Vertex peek() {
        return array.get(0);
    }

    public double getKey(Vertex v) {
        if (keys.containsKey(v)) {
            return keys.get(v);
        }
        return Double.NaN;
    }

    public void setKey(Vertex v, double k) {
        keys.put(v, k);
    }
}
