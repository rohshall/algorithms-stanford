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
    private List<List<Integer>> setSizes;
    private int n;
    private int setNumber;

    public TravellingSalesman(TSPInstance tspInstance) {
        this.tspInstance = tspInstance;
        this.n = tspInstance.getN();
        this.setNumber = (int) Math.pow(2, n-1);
        this.array = new double[setNumber][n];
        setSizes = new ArrayList<>();
        preprocess();
    }

    private void preprocess() {
        for (int i=1; i<setNumber; i++) {
            array[i][0] = Double.POSITIVE_INFINITY;
        }

        for (int m=0; m<n; m++) {
              setSizes.add(new ArrayList<Integer>());
        }
        for (int m=1; m<setNumber; m++) {
            List<Integer> list = citiesInSet(m);
            setSizes.get(list.size()).add(m);
        }

    }

    private void mainLoop() {
        for (int m=1; m<n; m++) {
            // for each set
            for (int s : setSizes.get(m)) {
                // for each j in s
                List<Integer> citiesInSet = citiesInSet(s);
                citiesInSet.add(0);
                for (int j : citiesInSet) {
                    if (j == 0) {
                        continue;
                    }
                    double min = Double.POSITIVE_INFINITY;
                    for (int k : citiesInSet) {
                        if (k == j) {
                            continue;
                        }
                        int twoPowN = (int) pow(2, j-1);
                        double val = array[s^twoPowN][k] + tspInstance.getDistanceBetweenTwoCities(k, j);
                        if (val < min) {
                            min = val;
                        }
                    }
                    array[s][j] = min;
                }
            }
        }
    }

    private double lastHop() {
        double min = Double.POSITIVE_INFINITY;
        for (int j = 1; j < n; j++) {
            double val = array[setNumber-1][j] + tspInstance.getDistanceBetweenTwoCities(j, 0);
            if (val < min) {
                min = val;
            }
        }

        return min;
    }

    private static List<Integer> citiesInSet(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            if (testNBit(n, i)) {
                result.add(i);
            }
        }

        return result;
    }

    private static boolean testNBit(int x, int n) {
        int twoPowN = (int) pow(2, n-1);
        return (x & twoPowN) == twoPowN;
    }

    public static void main(String[] args) {
        TSPInstance tspInstance1 = TSPReader.readFromFile("tsp/medium.txt");
        TravellingSalesman algorithm = new TravellingSalesman(tspInstance1);
        algorithm.mainLoop();
        System.out.println(algorithm.lastHop());
    }

}
