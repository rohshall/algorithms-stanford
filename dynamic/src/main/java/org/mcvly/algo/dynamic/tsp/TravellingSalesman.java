package org.mcvly.algo.dynamic.tsp;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

/**
 * @author <a href="mailto:RMalyona@luxoft.com">Ruslan Malyona</a>
 * @since 11.10.13
 */
public class TravellingSalesman {

    private TSPInstance tspInstance;
    private int[] sets;
    private double[][] array;
    private int n;
    private int setNumber;

    public TravellingSalesman(TSPInstance tspInstance) {
        this.tspInstance = tspInstance;
        this.n = tspInstance.getN();
        this.setNumber = (int) Math.pow(2, n-1);
        this.array = new double[setNumber][n];
        preprocess();
    }

    private void preprocess() {
        for (int i=0; i<setNumber; i++) {
            array[i][0] = Double.POSITIVE_INFINITY;
        }
    }

    private void mainLoop() {
        for (int i=1; i<setNumber; i++) {
            for (int j : citiesInSet(i)) {

            }
        }
    }

    private static List<Integer> citiesInSet(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i=1; i<n; i++) {
            if (testNBit(n, i)) {
                result.add(i);
            }
        }

        return result;
    }

    private static boolean testNBit(int x, int n) {
        int _2n = (int) pow(2, n-1);
        return (x & _2n) == _2n;
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(citiesInSet(13));
    }

}
