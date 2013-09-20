package org.mcvly.algo.greedy.clustering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 20.09.13
 */
public class HugeClusterProblem {

    private WeightedQuickUnionPathCompressionUF unionFind;
    private Set<BitSet> points;
    private Map<BitSet, Integer> mapping;
    private int spacing;
    private int bitCount;

    public HugeClusterProblem(String file, int spacing) throws IOException {
        this.spacing = spacing;
        initFromFile(file);
        unionFind = new WeightedQuickUnionPathCompressionUF(mapping.size());
    }

    public int clusterProblem()  {

        for (BitSet element : points) {
            Set<BitSet> candidates = generateSimilarities(element, spacing-1);
            for (BitSet c : candidates) {
                if (points.contains(c) && !unionFind.connected(mapping.get(element), mapping.get(c))) {
                    unionFind.union(mapping.get(element), mapping.get(c));
                }
            }
        }

        return unionFind.count();
    }

    private void initFromFile(String fileName) throws IOException {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(WeightedQuickUnionPathCompressionUF.class.getClassLoader().getResourceAsStream(fileName)))) {
            String s = r.readLine();
            //first line [number_of_nodes] [number_of_edges]
            String[] counts = s.split(" ");
            if (counts.length != 2) {
                throw new IOException("wrong format of count");
            }
            int nodesCount = Integer.parseInt(counts[0]);
            int bits = Integer.parseInt(counts[1]);
            this.bitCount = bits;

            points = new HashSet<>((int) (nodesCount / 0.75));
            mapping = new HashMap<>((int) (nodesCount / 0.75));

            s = r.readLine();
            int i = 0;
            while (s != null) {
                BitSet set = bitSetFromString(s, bits);
                if (!points.contains(set)) {
                    points.add(set);
                    mapping.put(set, i);
                    i++;
                }

                s = r.readLine();
            }
        }
    }

    private static BitSet bitSetFromString(String str, int size) {
        BitSet b = new BitSet(size);
        int i = 0;
        for (Character c : str.toCharArray()) {
            if (c == '0') {
                b.set(i, false);
                i++;
            } else if (c == '1') {
                b.set(i, true);
                i ++;
            }
        }

        return b;
    }

    private static int hammingDistance(BitSet a, BitSet b) {
        BitSet xor = (BitSet) a.clone();
        xor.xor(b);
        return xor.cardinality();
    }

    private Set<BitSet> generateSimilarities(BitSet node, int differences) {
        Set<BitSet> set = new HashSet<>();

        if (differences == bitCount) {
            BitSet toFlip = (BitSet) node.clone();
            toFlip.flip(0, bitCount);
            set.add(toFlip);
            return set;
        }

        if (differences == 1) {
            for (int i=0; i<bitCount; i++) {
                BitSet toFlip = (BitSet) node.clone();
                toFlip.flip(i);
                set.add(toFlip);
            }
        }

        if (differences > 1) {
            for (int i=0; i<bitCount; i++) {
                BitSet toFlip = (BitSet) node.clone();
                toFlip.flip(i);
                set.add(toFlip);
                set.addAll(generateSimilarities(toFlip, differences - 1));
            }
        }

        return set;
    }



    public static void main(String[] args) throws IOException {
        // clustering_big.txt 6118
        // hamming_2000 29
        // simple_hamming 4
        HugeClusterProblem problem = new HugeClusterProblem("clustering_big.txt", 3);
        System.out.println(problem.clusterProblem());
    }
}
