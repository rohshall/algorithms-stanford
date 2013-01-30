package org.mcvly.algo.dnq.matricies;

import java.util.Arrays;

/**
 * User: RMalyona
 * Date: 30.01.13
 */
public class NaiveMultiplication implements MatricesMultiplication{

    @Override
    public long[][] multiplyMatrices(long[][] a, long[][] b) {

        try {
            MatrixUtils.checkMatricesDimensions(a,b);
        } catch (IllegalAccessException e) {
            System.err.println(e.getMessage());
            return new long[0][0];
        }

        long[][] resultingMatrix = new long[a.length][a.length];

        for (int i=0; i<a.length; i++) {
            for (int j=0; j<a.length; j++) {
                resultingMatrix[i][j] = 0;
                for (int k=0; k<a.length; k++) {
                    resultingMatrix[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return resultingMatrix;
    }

    public static void main(String[] args) {

        long[][] x = {{1,2,3}, {4,5,6}, {7,8,9}};
        long[][] y = {{10,11,12}, {13,14,15}, {16,17,18}};

        long[][] xx = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        long[][] yy = {{17,18,19,20}, {21,22,23,24}, {25,26,27,28}, {29,30,31,32}};

        long[][] xxx = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25}};
        long[][] yyy = {{26,27,28,29,30}, {31,32,33,34,35}, {36,37,38,39,40}, {41,42,43,44,45}, {46,47,48,49,50}};

        long[][] x1 = {{1,2},{3,4}};
        long[][] y1 = {{5,6},{7,8}};

        long[][] r = new NaiveMultiplication().multiplyMatrices(x,y);

        System.out.println(MatrixUtils.printMatrix(r));
    }
}
