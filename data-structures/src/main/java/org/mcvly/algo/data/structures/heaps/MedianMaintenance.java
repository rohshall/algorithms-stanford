package org.mcvly.algo.data.structures.heaps;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * User: RMalyona
 * Date: 05.03.13
 */
public class MedianMaintenance {
    private PriorityQueue<Integer> minHeap, maxHeap;

    public MedianMaintenance() {
        this.maxHeap = new PriorityQueue<Integer>(); // natural ordering (i.e. min of max)
        this.minHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());
    }
    public int median(int nextNumber) {
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(nextNumber);
        } else {
            minHeap.add(nextNumber);
        }

        if (maxHeap.size() > 0 && maxHeap.peek() < minHeap.peek()) {
            // swap
            int t = minHeap.poll();
            minHeap.add(maxHeap.poll());
            maxHeap.add(t);
        }

        return minHeap.peek();
    }
}
