package org.mcvly.algo.graph;

import org.junit.Assert;
import org.junit.Test;
import org.mcvly.algo.graph.dijsktra.Heap;
import org.mcvly.algo.graph.dijsktra.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * User: RMalyona
 * Date: 02.03.13
 */
public class TestHeap {

    @Test
    public void testHeapify() {
        Heap<Vertex> heap1 = createHeap();
        testHeapProperty(heap1, 0);
    }

    @Test
    public void testMinHeapify() {
        Heap<Vertex> heap1 = createHeap();
        Vertex v1 = heap1.peek();
        heap1.setKey(v1, 12d);
        heap1.minHeapify(v1);
        testHeapProperty(heap1, 0);
    }

    @Test
    public void testDecreaseKey() {
        Heap<Vertex> heap1 = createHeap();
        Vertex v1 = heap1.get(heap1.size() - 1);
        heap1.decreaseKey(v1, 0d);
        testHeapProperty(heap1, 0);
    }

    @Test
    public void testInsert() {
        Heap<Vertex> heap1 = createHeap();
        Vertex v1 = new Vertex("0");
        heap1.insert(v1, 0d);
        testHeapProperty(heap1, 0);
    }

    @Test
    public void testExtractMin() {
        Heap heap1 = createHeap();
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");

        Assert.assertEquals(v1, heap1.extractMin());
        Assert.assertEquals(6, heap1.size());
        Assert.assertEquals(v2, heap1.extractMin());
        Assert.assertEquals(5, heap1.size());
        Assert.assertEquals(v3, heap1.extractMin());
        Assert.assertEquals(4, heap1.size());
        Assert.assertEquals(v4, heap1.extractMin());
        Assert.assertEquals(3, heap1.size());
        Assert.assertEquals(v5, heap1.extractMin());
        Assert.assertEquals(2, heap1.size());
        Assert.assertEquals(v6, heap1.extractMin());
        Assert.assertEquals(1, heap1.size());
        Assert.assertEquals(v7, heap1.extractMin());
        Assert.assertEquals(0, heap1.size());
    }

    public void testHeapProperty(Heap<Vertex> h, int i) {
        int l = h.left(i);
        int r = h.right(i);
        if (l < h.size()) {
            Assert.assertTrue(h.getKey(h.get(i)) < h.getKey(h.get(l)));
            testHeapProperty(h, l);
        }
        if (r < h.size()) {
            Assert.assertTrue(h.getKey(h.get(i)) < h.getKey(h.get(r)));
            testHeapProperty(h, r);
        }
    }

    private Heap<Vertex> createHeap() {
        Vertex v1 = new Vertex("5");
        Vertex v2 = new Vertex("3");
        Vertex v3 = new Vertex("4");
        Vertex v4 = new Vertex("2");
        Vertex v5 = new Vertex("6");
        Vertex v6 = new Vertex("7");
        Vertex v7 = new Vertex("1");

        List<Vertex> list = new ArrayList<Vertex>();
        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        list.add(v6);
        list.add(v7);
        Heap<Vertex> heap1 = new Heap<>();
        heap1.heapify(list);
        heap1.decreaseKey(v1, 5d);
        heap1.decreaseKey(v2, 3d);
        heap1.decreaseKey(v3, 4d);
        heap1.decreaseKey(v4, 2d);
        heap1.decreaseKey(v5, 6d);
        heap1.decreaseKey(v6, 7d);
        heap1.decreaseKey(v7, 1d);
        return heap1;
    }
}
