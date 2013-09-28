package org.mcvly.algo.dynamic.knapsack;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author: mcvly
 * @since: 9/27/13
 */
public class KnapsackAlgorithm {

    private long[][] array;
    private List<KnapsackItem> elements;

    public long solveKnapsackProblem(int capacity, List<KnapsackItem> items) {
        array = new long[items.size()+1][capacity+1];
        elements = new ArrayList<>(items);

        int value, size;

        for (int i = 1; i <= items.size(); i++) {
            value = elements.get(i-1).getValue();
            size = elements.get(i-1).getSize();
            for (int j = 1; j <= capacity ; j++) {
                array[i][j] = j < size ? array[i-1][j] : Math.max(array[i-1][j], array[i-1][j-size] + value);
            }
        }

        return array[items.size()][capacity];
    }

    public long solveProblemFast(int capacity, List<KnapsackItem> items) {
        long[] arrayA = new long[capacity+1];
        long[] arrayB = new long[capacity+1];

        int value, size;

        for (int i = 1; i <= items.size(); i++) {
            value = items.get(i-1).getValue();
            size = items.get(i-1).getSize();
            System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length);
            for (int j = 1; j<= capacity; j++) {
                arrayA[j] = j < size ? arrayB[j] : Math.max(arrayB[j], arrayB[j-size] + value);
            }
        }

        return arrayA[capacity];
    }

    public int[] getPoints() {
        List<Integer> points = new ArrayList<>();
        int n = array.length-1;
        int w = array[0].length-1;
        for (int i = n; i > 0; i--) {
            if (array[i][w] > array[i-1][w]) {
                points.add(i);
                w -= elements.get(i-1).getSize();
            }
        }

        int[] ar = new int[points.size()];
        for (int i = 0; i < points.size(); i++) {
            ar[points.size()-i-1] = points.get(i);
        }

        return ar;
    }

    public static void main(String[] args) throws IOException {
//        String fileName = "simple_knapsack.txt"; // 8
//        String fileName = "knapsack_50.txt"; // 142156
        String fileName = "knapsack1.txt"; // 2493893
//        String fileName = "knapsack_big.txt"; //4243395

        try (InputStream is = KnapsackAlgorithm.class.getClassLoader().getResourceAsStream(fileName)) {
            KnapsackReader reader = new KnapsackReader();
            List<KnapsackItem> items = reader.readItems(is);
            int capacity = reader.getCapacity();

            KnapsackAlgorithm algorithmStraightforward = new KnapsackAlgorithm();
            long sum = algorithmStraightforward.solveProblemFast(capacity, items);
            System.out.println("Sum is: " + sum);
//            for (int i = 0; i < algorithmStraightforward.array.length; i++) {
//                System.out.println(Arrays.toString(algorithmStraightforward.array[i]));
//            }

            //int[] resultItems = algorithmStraightforward.getPoints();
            //System.out.println("Points: " + Arrays.toString(resultItems));
        }
    }

}
