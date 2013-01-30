package org.mcvly.algo.dnq.matricies;

import static org.mcvly.algo.dnq.matricies.MatrixUtils.*;
/**
 * User: RMalyona
 * Date: 30.01.13
 */
public class StrassensMultiplication implements MatricesMultiplication {

    @Override
    public long[][] multiplyMatrices(long[][] a, long[][] b) {

        try {
            MatrixUtils.checkMatricesDimensions(a,b);
        } catch (IllegalAccessException e) {
            System.err.println(e.getMessage());
            return new long[0][0];
        }


        if (a.length % 2 == 1) {
            long[][] c = strassen(a,b);
            return shrinkMatrix(c);
        } else {
            return strassen(a, b);
        }
    }

    private long[][] strassen(long[][] a, long[][] b) {

        if (a.length == 1) {
            return new long[][] { {a[0][0] * b[0][0]} };
        }

        boolean extendFlag = a.length % 2 == 1;
        if (extendFlag) {
            a = completeMatrixToEvenN(a);
            b = completeMatrixToEvenN(b);
        }

        //split matrices into 4
        long[][][] aSplitted = splitMatrix(a);
        long[][][] bSplitted = splitMatrix(b);

        // Calculating p1 to p7:
        long[][] p1, p2, p3, p4, p5, p6, p7;
        p1 = strassen(aSplitted[0], subtract(bSplitted[1], bSplitted[3])); // p1 = A(F-H)
        p2 = strassen(add(aSplitted[0],aSplitted[1]), bSplitted[3]); // p2 = (A+B)H
        p3 = strassen(add(aSplitted[2],aSplitted[3]), bSplitted[0]);// p3 = (C+D)E
        p4 = strassen(aSplitted[3], subtract(bSplitted[2],bSplitted[0])); // p4 = D(G-E)
        p5 = strassen(add(aSplitted[0],aSplitted[3]), add(bSplitted[0],bSplitted[3])); // p5 = (A+D)(E+H)
        p6 = strassen(subtract(aSplitted[1],aSplitted[3]),  add(bSplitted[2],bSplitted[3])); // p6 = (B-D)(G+H)
        p7 = strassen(subtract(aSplitted[2],aSplitted[0]), add(bSplitted[0],bSplitted[1])); // p7 = (C-A)(E+F)

        long[][][] C = new long[4][][];
        C[0] = add(subtract(add(p5, p4), p2), p6); // p5+p4-p2+p6
        C[1] = add(p1, p2);
        C[2] = add(p3, p4);
        C[3] = add(subtract(add(p5,p1),p3),p7);    // p5+p1-p3+p7

        return mergeFinalMatrix(C, a.length);
    }

    private long[][] completeMatrixToEvenN(long[][] matrix) {
        if (matrix.length % 2 == 0) {
            return matrix;
        }

        long[][] resultingMatrix = new long[matrix.length+1][matrix.length+1];
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix.length; j++) {
                resultingMatrix[i][j] = matrix[i][j];
            }
            resultingMatrix[i][matrix.length]=0;
        }
        for (int j=0; j<matrix.length; j++) {
            resultingMatrix[matrix.length][j] = 0;
        }

        return resultingMatrix;
    }

    /**
     * removes extra row and column of zeros
     * @param matrix
     * @return
     */
    private long[][] shrinkMatrix(long[][] matrix) {
        if (matrix.length % 2 == 1) {
            return matrix;
        }

        long[][] resultingMatrix = new long[matrix.length-1][matrix.length-1];
        for (int i=0; i<matrix.length-1; i++) {
            for (int j=0; j<matrix.length-1; j++) {
                resultingMatrix[i][j] = matrix[i][j];
            }
        }

        return resultingMatrix;
    }

    private long[][][] splitMatrix(long[][] matrix) {
        int newSize = matrix.length/2;
        long[][][] result = new long[4][newSize][newSize];
        for (int i=0; i<newSize; i++) {
            for (int j=0; j<newSize; j++) {
                result[0][i][j] = matrix[i][j];
                result[1][i][j] = matrix[i][j + newSize];
                result[2][i][j] = matrix[i + newSize][j];
                result[3][i][j] = matrix[i + newSize][j + newSize];
            }
        }
        return result;
    }

    private long[][] mergeFinalMatrix(long[][][] matrices, int length) {
        long[][] c = new long[length][length];
        // Grouping the results obtained in a single matrix:
        for (int i = 0; i < length/2 ; i++) {
            for (int j = 0 ; j < length/2 ; j++) {
                c[i][j] = matrices[0][i][j];
                c[i][j + length/2] = matrices[1][i][j];
                c[i + length/2][j] = matrices[2][i][j];
                c[i + length/2][j + length/2] = matrices[3][i][j];
            }
        }

        return c;
    }

    public static void main(String[] args) {
        long[][] x1 = {{1,2},{3,4}};
        long[][] y1 = {{5,6},{7,8}};

        long[][] x = {{1,2,3}, {4,5,6}, {7,8,9}};
        long[][] y = {{10,11,12}, {13,14,15}, {16,17,18}};

        long[][] xx = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        long[][] yy = {{17,18,19,20}, {21,22,23,24}, {25,26,27,28}, {29,30,31,32}};

        long[][] xxx = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25}};
        long[][] yyy = {{26,27,28,29,30}, {31,32,33,34,35}, {36,37,38,39,40}, {41,42,43,44,45}, {46,47,48,49,50}};

        long[][] r = new StrassensMultiplication().multiplyMatrices(x,y);

        System.out.println(MatrixUtils.printMatrix(r));
    }
}
