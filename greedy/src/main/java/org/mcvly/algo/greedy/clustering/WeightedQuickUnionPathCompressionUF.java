package org.mcvly.algo.greedy.clustering;

/****************************************************************************
 *  Compilation:  javac WeightedQuickUnionPathCompressionUF.java
 *  Execution:  java WeightedQuickUnionPathCompressionUF < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *
 *  Weighted quick-union with path compression.
 *
 ****************************************************************************/

public class WeightedQuickUnionPathCompressionUF {
    private int[] id;    // id[i] = parent of i
    private int[] sz;    // sz[i] = number of objects in subtree rooted at i
    private int count;   // number of components

    // Create an empty union-find data structure with N isolated sets 0 through N-1.
    public WeightedQuickUnionPathCompressionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // Return the number of components.
    public int count() {
        return count;
    }

    // Return component identifier for component containing p.
    public int find(int p) {
        int root = p;
        while (root != id[root])
            root = id[root];
        while (p != root) {
            int newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
    }

    // Are objects p and q in the same component?
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    // Merge components containing p and q.
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make smaller root point to larger one
        if   (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else                 { id[j] = i; sz[i] += sz[j]; }
        count--;
    }

}

